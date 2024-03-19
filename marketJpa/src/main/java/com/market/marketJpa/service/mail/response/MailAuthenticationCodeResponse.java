package com.market.marketJpa.service.mail.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class MailAuthenticationCodeResponse {

    private String email;

    private String authenticationCode;

    @Builder
    public MailAuthenticationCodeResponse(String email, String authenticationCode) {
        this.email = email;
        this.authenticationCode = authenticationCode;
    }

    public static MailAuthenticationCodeResponse of(String email, String authenticationCode) {
        return MailAuthenticationCodeResponse.builder()
            .email(email)
            .authenticationCode(authenticationCode)
            .build();
    }
}
