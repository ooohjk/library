package com.example.library.domain.user.dto;

import com.example.library.domain.review.dto.ReviewDto;
import com.example.library.domain.review.entity.ReviewEntity;
import com.example.library.domain.user.enums.SocialLoginType;
import com.example.library.domain.user.enums.UserGrade;
import com.example.library.domain.user.entity.UserEntity;
import jakarta.validation.constraints.Size;
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

    public UserSearchResDto(UserEntity user) {
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
                    .map(m -> new ReviewDto(m.getBook().getBookCode(), m.getUser().getUserNo(), m.getRegDate(), m.getReviewContent()))
                    .collect(Collectors.toList());
        }
    }

    public UserSearchResDto
            (Long userNo, @Size(min = 4, max = 10) String userId, @Size(min = 2, max = 5) String userName,
             String tel, String userEmail, SocialLoginType provider, String providerId, String gender,
             Integer useFlg, UserGrade userGrade, List<ReviewEntity> review)
    {
        this.userNo = userNo;
        this.userId = userId;
        this.userName = userName;
        this.tel = tel;
        this.userEmail = userEmail;
        this.provider = provider;
        this.gender = gender;
        this.useFlg = useFlg;
        this.userGrade = userGrade;
        this.providerId = providerId;
        if (review == null) {
            review = new ArrayList<>();
            this.review = review.stream()
                    .map(m -> new ReviewDto())
                    .collect(Collectors.toList());
        } else {
            this.review = review.stream()
                    .map(m -> new ReviewDto(m.getBook().getBookCode(), m.getUser().getUserNo(), m.getRegDate(), m.getReviewContent()))
                    .collect(Collectors.toList());
        }
    }

    public static UserSearchResDto from(UserEntity user){
        return new UserSearchResDto(user);
    }
}
