package com.example.library.user.service.Impl;

import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;
import com.example.library.global.security.oauth2.principal.CustomOAuth2User;
import com.example.library.send.sendMail;
import com.example.library.user.dto.*;
import com.example.library.user.entity.UserEntity;
import com.example.library.user.enums.SocialLoginType;
import com.example.library.user.enums.UserGrade;
import com.example.library.user.repository.UserRepository;
import com.example.library.user.service.UserService;
import com.example.library.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService , OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public void join(UserJoinReqDto userJoinReqDto) {
        userRepository.findByUserId(userJoinReqDto.getUserId()).ifPresent(user -> {
            throw new AppException(ErrorCode.USERID_DUPLICATED, userJoinReqDto.getUserId() + " 는 이미 존재합니다.");
        });

        UserEntity user =UserEntity.createOfficialUser()
                .userId(userJoinReqDto.getUserId())
                .userPwd(encoder.encode(userJoinReqDto.getUserPwd()))
                .userName(userJoinReqDto.getUserName())
                .tel(userJoinReqDto.getTel())
                .userEmail(userJoinReqDto.getEmail())
                .gender(userJoinReqDto.getGender())
                .useFlg(userJoinReqDto.getUseFlg())
                .userGrade(UserGrade.OFFICIALMEMBER)
                .build()
        ;

        sendMail mail = sendMail.send("join", userJoinReqDto.getEmail());

        userRepository.save(user);
    }

    public UserLoginResDto login(UserLoginReqDto userLoginReqDto) {
            UserEntity selectedUser = userRepository.findByUserId(userLoginReqDto.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USERID_NOT_FOUND, userLoginReqDto.getUserId() + "이 없습니다."));

            if(!encoder.matches(userLoginReqDto.getUserPwd(), selectedUser.getUserPwd())) {
                throw new AppException(ErrorCode.INVALID_PASSWORD, "패스워드를 잘못 입력 하였습니다.");
            }

            String token = JwtUtil.createJwt(selectedUser.getUserId());

            return UserLoginResDto.from(selectedUser,token);
    }

    @Override
    public UserSearchResDto getUserByUserNo(Long userNo) {
        UserEntity userEntity = userRepository.findByUserNo(userNo)
                .orElseThrow(()->new AppException(ErrorCode.USERID_NOT_FOUND,"존재하지 않는 유저번호입니다."));

        return UserSearchResDto.from(userEntity);
    }

    @Override
    public UserSearchResDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USERID_NOT_FOUND, userId + "이 없습니다."));

        return UserSearchResDto.from(userEntity);
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

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        String providerId = oAuth2User.getAttribute(userNameAttributeName);

        //2. 당사 존재 여부 파악 (조회 조건: 소셜플랫폼 종류, email, socialId )
        UserEntity saved = userRepository.findByProviderAndProviderIdAndUserEmail(socialLoginType,providerId,email);

        //3. 존재하지 않는 경우 자동 회원가입 진행
        if(saved == null){
            UserEntity userEntityBySocialLogin = UserEntity.createOAuth2User()
                    .userId(email)
                    .userPwd(encoder.encode("tempPwd"))
                    .userEmail(email)
                    .userName(name)
                    .providerId(providerId)
                    .provider(socialLoginType)
                    .build();
            UserEntity savedEntityBySocialLogin = userRepository.save(userEntityBySocialLogin);
            return new CustomOAuth2User(
                        Collections.singleton(new SimpleGrantedAuthority(null)),
                        oAuth2User.getAttributes(),
                        userNameAttributeName,
                        savedEntityBySocialLogin
                    );
        }
        else{
            return new CustomOAuth2User(
                        Collections.singleton(new SimpleGrantedAuthority("ROLE")),
                        oAuth2User.getAttributes(),
                        userNameAttributeName,
                        saved
                    );
        }

    }

    private String getDataFromOAuth2User(OAuth2User oAuth2User,String type){
        return (String)oAuth2User.getAttributes().get(type);
    }
}
