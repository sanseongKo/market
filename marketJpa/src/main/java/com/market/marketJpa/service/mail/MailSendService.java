package com.market.marketJpa.service.mail;

import com.market.marketJpa.repository.user.EmailAuthenticationRepository;
import com.market.marketJpa.service.constant.ConstantValue;
import com.market.marketJpa.service.mail.request.MailAuthenticationCodeRequest;
import com.market.marketJpa.service.mail.response.MailAuthenticationCodeResponse;
import com.market.marketJpa.vo.user.EmailAuthentication;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailSendService {

    private final MailSendClient mailSendClient;
    private final EmailAuthenticationRepository emailAuthenticationRepository;

    public MailAuthenticationCodeResponse sendAuthenticationCodeTo(
        MailAuthenticationCodeRequest request
    ) throws MessagingException {
        EmailAuthentication entity = request.toEntity();
        EmailAuthentication savedEntity = emailAuthenticationRepository.save(entity);

        mailSendClient.sendMail(savedEntity.getEmail(), ConstantValue.MAIL_CONTENT);

        return MailAuthenticationCodeResponse.of(savedEntity.getEmail(), savedEntity.getAuthenticationCode());
    }


}
