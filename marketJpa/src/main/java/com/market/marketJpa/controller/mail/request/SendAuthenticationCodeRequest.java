package com.market.marketJpa.controller.mail.request;

import com.market.marketJpa.service.mail.request.MailAuthenticationCodeRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendAuthenticationCodeRequest {

    @NotBlank
    @Email
    private String email;

    @Builder
    public SendAuthenticationCodeRequest(String email) {
        this.email = email;
    }

    public MailAuthenticationCodeRequest toServiceRequest(String authenticationCode) {
        return MailAuthenticationCodeRequest.builder()
            .email(email)
            .authenticationCode(authenticationCode)
            .build();
    }
}
