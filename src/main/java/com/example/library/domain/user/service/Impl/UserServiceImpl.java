package com.example.library.domain.user.service.Impl;

import com.example.library.domain.book.entity.BookEntity;
import com.example.library.domain.book.service.BookService;
import com.example.library.domain.user.dto.*;
import com.example.library.domain.user.entity.Heart;
import com.example.library.domain.user.entity.UserEntity;
import com.example.library.domain.user.enums.SocialLoginType;
import com.example.library.domain.user.enums.UserGrade;
import com.example.library.domain.user.repository.HeartRepository;
import com.example.library.domain.user.repository.UserRepository;
import com.example.library.domain.user.service.UserService;
import com.example.library.domain.user.service.dto.HeartResponseDto;
import com.example.library.exception.AppException;
import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.*;
import com.example.library.global.mail.sendMail;
import com.example.library.global.security.oauth2.principal.CustomOAuth2User;
import com.example.library.global.security.oauth2.userInfo.CustomOAuthAttributes;
import com.example.library.global.utils.JwtUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final BookService bookService;
    private final UserRepository userRepository;
    private final HeartRepository heartRepository;
    private final BCryptPasswordEncoder encoder;
    @PersistenceContext
    private EntityManager entityManager;

    public void join(UserJoinReqDto userJoinReqDto) {
        userRepository.findByUserId(userJoinReqDto.getUserId()).ifPresent(user -> {
            throw new UserIdDuplicateException(ErrorCode.USERID_DUPLICATED);
        });

        UserEntity user = UserEntity.createOfficialUser()
                .userId(userJoinReqDto.getUserId())
                .userPwd(encoder.encode(userJoinReqDto.getUserPwd()))
                .userName(userJoinReqDto.getUserName())
                .tel(userJoinReqDto.getTel())
                .userEmail(userJoinReqDto.getEmail())
                .gender(userJoinReqDto.getGender())
                .useFlg(userJoinReqDto.getUseFlg())
                .userGrade(UserGrade.OFFICIALMEMBER)
                .review(new ArrayList<>())
                .build()
        ;
        userRepository.save(user);

        sendMail.send("join", userJoinReqDto.getEmail());
    }

    public UserLoginResDto login(UserLoginReqDto userLoginReqDto) {
        UserEntity selectedUser = userRepository.findByUserId(userLoginReqDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USERID_NOT_FOUND));

        if(!encoder.matches(userLoginReqDto.getUserPwd(), selectedUser.getUserPwd())) {
            throw new PasswordDifferentException(ErrorCode.PASSWORD_DIFFERNET);
        }

        String token = JwtUtil.createJwt(selectedUser.getUserId());
        sendMail.send("login", selectedUser.getUserEmail());

        return UserLoginResDto.from(selectedUser,token);
    }

    @Override
    public UserSearchResDto getUserByUserNo(Long userNo) {
        UserEntity userEntity = userRepository.findByUserNo(userNo)
                .orElseThrow(()->new UserNotFoundException(ErrorCode.USERNO_NOT_FOUND));

        return UserSearchResDto.from(userEntity);
    }

//    @Transactional
    @Override
    public UserSearchResDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USERID_NOT_FOUND));
        return UserSearchResDto.from(userEntity);
    }

    @Override
    public UserGrade getUserGrade(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USERID_NOT_FOUND));

        return userEntity.getUserGrade();
    }

    public String getUserNameByEmail(String userEmail) {
        UserEntity userEntity = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new AppException(ErrorCode.MAIL_NOT_FOUND));

        return UserSearchResDto.from(userEntity).getUserName();
    }

    @Override
    public UserSearchResDto update(String userId, UserUpdateDto userUpdateDto) {
        UserEntity selectedUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USERID_NOT_FOUND));

        selectedUser.setUserPwd(encoder.encode(userUpdateDto.getUserPwd()));
        selectedUser.setUserName(userUpdateDto.getUserName());
        selectedUser.setTel(userUpdateDto.getTel());
        selectedUser.setUserEmail(userUpdateDto.getEmail());
        selectedUser.setGender(userUpdateDto.getGender());
        selectedUser.setUseFlg(userUpdateDto.getUseFlg());

        userRepository.save(selectedUser);

        return UserSearchResDto.from(selectedUser);
    }

    @Override
    @Transactional
    public void delete(String userId) {
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        userRepository.deleteByUserId(userId);
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

    @Override
    public List<UserSearchResDto> getAllUsers() {
        List<UserEntity> user = userRepository.findAll();

        return user.stream()
                .map(m -> new UserSearchResDto(
                        m.getUserNo(), m.getUserId(), m.getUserName(), m.getTel(), m.getUserEmail(), m.getProvider(),
                        m.getProviderId(), m.getGender(), m.getUseFlg(), m.getUserGrade(), m.getReview()
                    )
                )
                .collect(Collectors.toList());
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

        //1. 당사에 가입된 유저 확인을 위한 데이터 추출
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        SocialLoginType socialLoginType = SocialLoginType.getSocialType(userRequest.getClientRegistration().getRegistrationId());
        Map<String,Object> attributes = oAuth2User.getAttributes();

        /* 플랫폼 종류에 따라 응답받은 유저 정보를 통해 CustomOAuthAttributes 객체 생성 */
        CustomOAuthAttributes customOAuthAttributes = CustomOAuthAttributes.of(socialLoginType,userNameAttributeName,attributes);


        //2. 당사 존재 여부 파악 (조회 조건: 소셜플랫폼 종류, email, socialId )
        UserEntity saved = userRepository.findByProviderAndProviderIdAndUserEmail
                (
                    socialLoginType,
                    customOAuthAttributes.getOAuthUserInfo().getProviderId(),
                    customOAuthAttributes.getOAuthUserInfo().getEmail()
                );

        //3. 존재하지 않는 경우 자동 회원가입/ 존재하는 경우 패스
        if(saved == null){
            UserEntity userEntityBySocialLogin = CustomOAuthAttributes.toEntity(socialLoginType, customOAuthAttributes.getOAuthUserInfo());
            UserEntity savedUserEntityBySocialLogin = userRepository.save(userEntityBySocialLogin);

            return new CustomOAuth2User(
                        Collections.singleton(new SimpleGrantedAuthority("ROLE")),
                        oAuth2User.getAttributes(),
                        userNameAttributeName,
                    savedUserEntityBySocialLogin
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

    @Override
    public UserSelectHeartResDto getMyHeartList(Long userNo) {
        //1. 유저번호로 유저 조회
        UserEntity selectedUser = getUserByUserNoMethod(userNo);
        List<Heart> heartList = selectedUser.getHeartList();

        //2. 조회된 유저엔티티 내 연관매핑된 Heart엔티티들 dto로 변환 작업
        List<HeartResponseDto> heartResponseDtoList = heartList.stream()
                .map(heart->HeartResponseDto.from(heart))
                .collect(Collectors.toList())
            ;

        log.info("유저번호["+userNo +"] / 찜도서 갯수["+heartResponseDtoList.size()+"권] 조회 성공");
        return UserSelectHeartResDto.builder()
                .userNo(userNo)
                .heartList(heartResponseDtoList)
                .build()
                ;
    }

    @Override
    public void registerHeartBook(UserHeartBookReqDto userHeartBookReqDto) {
        Long userNo = userHeartBookReqDto.getUserNo();
        Long bookCode = userHeartBookReqDto.getBookCode();
        //1. 유저번호로 유저 조회
        UserEntity selectedUser = getUserByUserNoMethod(userNo);
        //2. 도서 존재 여부 조회
        BookEntity selectedBook = bookService.getBookDetail(bookCode);
        //3. 유저의 특정 도서 중복 찜 여부 파악
        checkAlreadyHeartBook(selectedUser,selectedBook);
        //4. 하트 엔티티 생성
        Heart heart = Heart.builder()
                    .book(selectedBook)
                    .user(selectedUser)
                    .build()
                ;
//        아래 두 라인 없어도 heartRepository에 저장은 되나 아래와 같은 문제가 있다.
//        selectedUser.heartBook(heart);   // 해당 객체의 heartList에 새로운 heart엔티티가 추가되지 않는 문제.. 즉, 정합성 문제 발생
//        userRepository.save(selectedUser); // 이건 모르겠다.

        heartRepository.save(heart);
        log.info("유저번호["+userNo +"] / 도서번호["+bookCode+"] 찜 성공");
    }

    @Override
    public void removeHeartBook(UserRemoveHeartBookReqDto userHeartBookReqDto) {
        Long userNo = userHeartBookReqDto.getUserNo();
        Long heartNo = userHeartBookReqDto.getHeartNo();
        //1. 유저번호로 유저 조회
        UserEntity selectedUser = getUserByUserNoMethod(userNo);

        //2. 찜번호로 찜 엔티티 조회
        Heart heart = heartRepository.findByHeartNo(heartNo)
                .orElseThrow(()->new HeartBookNotFoundException(ErrorCode.HEARTNO_NOT_FOUND));

        heartRepository.delete(heart);
        log.info("유저번호["+userNo +"] / 찜번호["+heart+"] 찜 해제 성공");
    }

    /**
     * 유저의 특정 책 중복찜 여부 파악
     * @param user
     * @param book
     * @return throw HeartBookAlreadyException
     */
    private void checkAlreadyHeartBook(UserEntity user, BookEntity book){
        heartRepository.findByUserAndAndBook(user, book)
                .ifPresent(heart -> {
                    throw new HeartBookAlreadyException(ErrorCode.HEARTBOOK_ALREADY);
                })
        ;
    }

    /**
     * 유저번호로 유저엔티티 조회
     * @param userNo
     * @return throw UserNotFoundException
     */
    private UserEntity getUserByUserNoMethod(Long userNo) {
        return userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.USERNO_NOT_FOUND));
    }
}
