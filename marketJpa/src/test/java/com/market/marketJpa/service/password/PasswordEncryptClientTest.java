package com.market.marketJpa.service.password;

import com.market.marketJpa.service.password.generator.PasswordSaltValueGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PasswordEncryptClientTest {
    @Autowired
    private PasswordSaltValueGenerator generator;
    @Autowired
    private PasswordEncryptClient client;

    @DisplayName("salt 값과 password 값이 있다면 같은 결과의 SHA-256으로 암호화된 비밀번호를 얻을 수 있다.")
    @Test
    void encryptBy() {
        //given
        String password = "test_password";
        String salt = generator.getSalt();

        //when
        String encryptedPassword = client.encryptBy(password, salt);
        String encryptedPasswordExpect = client.encryptBy(password, salt);

        //then
        assertThat(encryptedPassword).isEqualTo(encryptedPasswordExpect);
    }
}
