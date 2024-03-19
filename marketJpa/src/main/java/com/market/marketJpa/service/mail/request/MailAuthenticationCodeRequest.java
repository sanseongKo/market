package com.market.marketJpa.service.mail.request;

import com.market.marketJpa.vo.user.EmailAuthentication;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MailAuthenticationCodeRequest {

    private String email;
    private String authenticationCode;

    @Builder
    public MailAuthenticationCodeRequest(String email, String authenticationCode) {
        this.email = email;
        this.authenticationCode = authenticationCode;
    }

    public EmailAuthentication toEntity() {
        return EmailAuthentication.builder()
            .email(email)
            .authenticationCode(authenticationCode).build();
    }
}
