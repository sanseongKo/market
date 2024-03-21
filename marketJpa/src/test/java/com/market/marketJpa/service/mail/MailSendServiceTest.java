package com.market.marketJpa.service.mail;

import com.market.marketJpa.service.mail.generator.MailAuthenticationCodeGenerator;
import com.market.marketJpa.repository.user.EmailAuthenticationRepository;
import com.market.marketJpa.service.mail.request.MailAuthenticationCodeRequest;
import com.market.marketJpa.service.mail.response.MailAuthenticationCodeResponse;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MailSendServiceTest {

    @Autowired
    private MailAuthenticationCodeGenerator generator;

    @Autowired
    private EmailAuthenticationRepository emailAuthenticationRepository;

    @Autowired
    private MailSendService mailSendService;

    @MockBean
    private MailSendClient mailSendClient;

    @AfterEach
    void tearDown() {
        emailAuthenticationRepository.deleteAllInBatch();
    }

    @DisplayName("인증 코드를 생성하면, 저장되고 메일을 발송한다.")
    @Test
    void sendAuthenticationCodeTo() throws MessagingException {
        //given
        String email = "test@test.com";
        String authenticationCode = generator.createAuthenticationCode();
        MailAuthenticationCodeRequest request =
                MailAuthenticationCodeRequest.builder()
                        .email(email)
                        .authenticationCode(authenticationCode)
                        .build();

        //when
        MailAuthenticationCodeResponse response = mailSendService.sendAuthenticationCodeTo(request);

        //then
        assertThat(response).extracting("email", "authenticationCode")
                .containsExactlyInAnyOrder(email, authenticationCode);
    }
}
