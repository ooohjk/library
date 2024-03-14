package com.example.library.domain.user.dto;

import com.example.library.domain.review.dto.ReviewDto;
import com.example.library.domain.user.enums.SocialLoginType;
import com.example.library.domain.user.enums.UserGrade;
import com.example.library.domain.user.entity.UserEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserSearchResDto {
    private Long userNo;

    private String userId;

    private String userName;

    private String tel;

    private String userEmail;

    private SocialLoginType provider;

    private String providerId;

    private String gender;

    private Integer useFlg;

    private UserGrade userGrade;

    private List<ReviewDto> review;

    private UserSearchResDto(UserEntity user) {
        this.userNo = user.getUserNo();
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.tel = user.getTel();
        this.userEmail = user.getUserEmail();
        this.provider = user.getProvider();
        this.gender = user.getGender();
        this.useFlg = user.getUseFlg();
        this.userGrade = user.getUserGrade();
        this.providerId = user.getProviderId();
        if (user.getReview() == null) {
            user.setReview(new ArrayList<>());
            this.review = user.getReview().stream()
                    .map(m -> new ReviewDto())
                    .collect(Collectors.toList());
        } else {
            this.review = user.getReview().stream()
                    .map(m -> new ReviewDto(m.getBook().getBookCode(), m.getUser().getUserNo(), m.getReviewContent()))
                    .collect(Collectors.toList());
        }
    }

    public static UserSearchResDto from(UserEntity user){
        return new UserSearchResDto(user);
    }
}
