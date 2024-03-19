package com.market.marketJpa.controller.user.request;

import com.market.marketJpa.service.user.request.SocialSignUpServiceRequest;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SocialSignUpRequest {
    private static final String REG_EXP = "^010-\\d{4}-\\d{4}$";

    @Email(message = "이메일 형식으로 작성해주세요.")
    @NotBlank(message = "이메일이 없습니다.")
    private String email;
    @NotBlank(message = "이름이 없습니다.")
    private String name;
    @NotBlank(message = "거주지의 시가 없습니다.")
    private String city;
    @NotBlank(message = "거주지의 구가 없습니다.")
    private String gu;
    @NotBlank(message = "거주지의 동이 없습니다.")
    private String dong;
    @NotNull(message = "생일이 없습니다.")
    private LocalDate birth;
    @NotBlank(message = "핸드폰 번호가 없습니다.")
    @Pattern(regexp = REG_EXP, message = "핸드폰 번호 형식이 아닙니다.")
    private String phoneNumber;
    @NotBlank(message = "닉네임이 없습니다.")
    private String nickname;
    @NotBlank(message = "소셜 로그인 플랫폼 명이 없습니다.")
    private String platformName;
    @NotBlank(message = "소셜 로그인의 Access Token이 없습니다.")
    private String socialAccessToken;
    @Nullable
    private MultipartFile image;

    @Builder
    public SocialSignUpRequest(String email, String name, String city, String gu, String dong, LocalDate birth, String phoneNumber, String nickname, String platformName, String socialAccessToken, @Nullable MultipartFile image) {
        this.email = email;
        this.name = name;
        this.city = city;
        this.gu = gu;
        this.dong = dong;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.platformName = platformName;
        this.socialAccessToken = socialAccessToken;
        this.image = image;
    }

    public SocialSignUpServiceRequest toServiceRequest() {
        return SocialSignUpServiceRequest.builder()
                .birth(birth)
                .city(city)
                .dong(dong)
                .gu(gu)
                .name(name)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .platformName(platformName)
                .socialAccessToken(socialAccessToken)
                .email(email)
                .image(image)
                .build();
    }
}
