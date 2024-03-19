package com.market.marketJpa.controller.mail;

import com.market.marketJpa.config.mail.MailAuthenticationCodeGenerator;
import com.market.marketJpa.controller.mail.request.SendAuthenticationCodeRequest;
import com.market.marketJpa.service.mail.MailSendService;

import com.market.marketJpa.service.mail.response.MailAuthenticationCodeResponse;
import com.market.marketJpa.vo.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import static com.market.marketJpa.controller.constant.UriConstantValue.AUTHENTICATION_CODE;
import static com.market.marketJpa.controller.constant.UriConstantValue.MAILS;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAILS)
public class MailAuthenticationController {

    private final MailAuthenticationCodeGenerator generator;
    private final MailSendService mailSendService;

    @PostMapping(AUTHENTICATION_CODE)
    public MailAuthenticationCodeResponse sendAuthenticationCode(@RequestBody SendAuthenticationCodeRequest request) throws MessagingException {
        String authenticationCode = generator.createAuthenticationCode();

        return mailSendService.sendAuthenticationCodeTo(request.toServiceRequest(authenticationCode));
    }
}
