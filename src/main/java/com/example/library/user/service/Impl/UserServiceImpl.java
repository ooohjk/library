package com.example.library.user.service.Impl;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;
import com.example.library.user.entity.UserEntity;
import com.example.library.user.dto.UserDto;
import com.example.library.user.enumPk.SocialLoginType;
import com.example.library.user.enumPk.UserGrade;
import com.example.library.user.repository.UserRepository;
import com.example.library.user.service.UserService;
import com.example.library.utils.JwtUtil;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService , OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret}")
    private String secret;
    private final Long expiredMs = 1000 * 60 * 60L;

    public String join(String userId, String userPwd, String userName, String tel, String email, String gender, Integer userFlg) {
        userRepository.findByUserId(userId).ifPresent(user -> {
            throw new AppException(ErrorCode.USERID_DUPLICATED, userId + " 는 이미 존재합니다.");
        });
        UserEntity userEntity =
                UserEntity.builder()
                        .userId(userId)
                        .userPwd(encoder.encode(userPwd))
                        .userName(userName)
                        .tel(tel)
                        .userEmail(email)
                        .gender(gender)
                        .useFlg(userFlg)
                        .userGrade(UserGrade.OFFICIALMEMBER)
                        .build();
        userRepository.save(userEntity);
        return "Success!!";
    }

    public String login(String userId, String userPwd) {
        UserEntity selectedUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USERID_NOT_FOUND, userId + "이 없습니다."));

        if(!encoder.matches(userPwd, selectedUser.getUserPwd())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 하였습니다.");
        }

        String token = JwtUtil.createJwt(selectedUser.getUserId(), secret, expiredMs);

        return token;
    }

    @Override
    public UserDto getUserByUserNo(Long userNo) {
        UserEntity userEntity = userRepository.findByUserNo(userNo).get();

        UserDto userDto = new UserDto(userEntity.getUserId(), userEntity.getUserPwd(), userEntity.getUserName(), userEntity.getTel(), userEntity.getUserEmail(), userEntity.getGender(), userEntity.getUseFlg());
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USERID_NOT_FOUND, userId + "이 없습니다."));;

        UserDto userDto = new UserDto(userEntity.getUserId(), userEntity.getUserPwd(), userEntity.getUserName(), userEntity.getTel(), userEntity.getUserEmail(), userEntity.getGender(), userEntity.getUseFlg());
        return userDto;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        /*
         * DefaultOAut2UserService 객체 생성 후 해당 객체를 통하여 loadUser메소드를 통해 OAuth2User 반환
         * DefaultOAut2UserService.loadUser()메소드는 역할은 간략히 아래 순서로 흐른다
         * 1. 소셜로그인 API의 사용자 정보를 위한 통신
         * 2. 소셜로그인 API로부터 응답 받은 사용자 정보로 DefaultOAuth2User 객체 생성 후 반환
         * 따라서!! OAuth2User객체는 사용자 정보가 담긴 객체!
         */
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //1. 당사에 가입된 유저 확인을 데이터 추출
        SocialLoginType socialLoginType = SocialLoginType.getSocialType(userRequest.getClientRegistration().getRegistrationId());
        String email = getDataFromOAuth2User(oAuth2User,"email");
        String name = getDataFromOAuth2User(oAuth2User,"name");
        String providerId = oAuth2User.getAttribute(userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName());

        //2. 당사 존재 여부 파악 (조회 조건: 소셜플랫폼 종류, email, socialId )
        Optional<UserEntity> saved = userRepository.findByProviderAndProviderIdAndUserEmail(socialLoginType,providerId,email);

        //3. 존재하지 않는 경우 자동 회원가입 진행
        if(saved.isEmpty()){
            UserEntity userEntityBySocialLogin = UserEntity.createOAuth2User().userEmail(email).userName(name).providerId(providerId).provider(socialLoginType).build();
            userRepository.save(userEntityBySocialLogin);
        }

        return oAuth2User;
    }

    private String getDataFromOAuth2User(OAuth2User oAuth2User,String type){
        return (String)oAuth2User.getAttributes().get(type);
    }
}
