package com.market.marketJpa.config.mail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MailAuthenticationCodeGeneratorTest {

    @Autowired
    private MailAuthenticationCodeGenerator generator;

    @DisplayName("이메일 검증 코드를 발급하면, 무조건 6자리여야 한다.")
    @Test
    void createAuthenticationCode() {
        //given
        //when
        String authenticationCode = generator.createAuthenticationCode();

        //then
        System.out.println(authenticationCode);
        assertThat(authenticationCode).hasSize(6);
    }
}
