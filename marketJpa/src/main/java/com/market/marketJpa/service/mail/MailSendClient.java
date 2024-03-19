package com.market.marketJpa.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailSendClient {
    private final JavaMailSender javaMailSender;

    //TODO(contant value로 관리 필요)
    public void sendMail(String toEmail, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom("no-reply@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject("MAIL_TITLE_CERTIFICATION");
        helper.setText(content, true);
        javaMailSender.send(mimeMessage);
    }
}
