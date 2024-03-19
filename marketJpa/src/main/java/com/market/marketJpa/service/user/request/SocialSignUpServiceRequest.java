package com.market.marketJpa.service.user.request;

import com.market.marketJpa.vo.image.Image;
import com.market.marketJpa.vo.user.SignUpType;
import com.market.marketJpa.vo.user.SocialLogin;
import com.market.marketJpa.vo.user.UserProfile;
import com.market.marketJpa.vo.user.Users;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Builder
public class SocialSignUpServiceRequest {
    private String email;
    private String name;
    private String city;
    private String gu;
    private String dong;
    private LocalDate birth;
    private String phoneNumber;
    private String nickname;
    private String platformName;
    private String socialAccessToken;
    private MultipartFile image;

    public UserProfile toUserProfileEntity(Users user, @Nullable Image image) {
        return UserProfile.builder()
                .name(name)
                .city(city)
                .dong(dong)
                .gu(gu)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .nickname(nickname)
                .image(image)
                .users(user)
                .build();
    }

    public Users toUsersEntity() {
        return Users.builder()
                .email(email)
                .signUpType(SignUpType.SOCIAL)
                .build();
    }

    public SocialLogin toSocialLoginEntity(Users user) {
        return SocialLogin.builder()
                .platformName(platformName)
                .accessToken(socialAccessToken)
                .user(user)
                .build();
    }
}
