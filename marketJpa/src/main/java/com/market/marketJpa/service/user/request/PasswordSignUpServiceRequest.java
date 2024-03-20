package com.market.marketJpa.service.user.request;

import com.market.marketJpa.vo.image.Image;
import com.market.marketJpa.vo.user.PasswordLogin;
import com.market.marketJpa.vo.user.SignUpType;
import com.market.marketJpa.vo.user.UserProfile;
import com.market.marketJpa.vo.user.Users;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Builder
@Getter
public class PasswordSignUpServiceRequest {
    private String email;
    private String name;
    private String city;
    private String gu;
    private String dong;
    private LocalDate birth;
    private String phoneNumber;
    private String nickname;
    private String password;
    private MultipartFile image;

    public Users toUsersEntity() {
        return Users.builder()
                .email(email)
                .signUpType(SignUpType.PASSWORD)
                .build();
    }

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

    public PasswordLogin toPasswordLoginEntity(Users users) {
        return PasswordLogin.builder()
                .password(password)
                .users(users)
                .build();
    }
}
