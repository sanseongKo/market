package com.market.marketJpa.service.password;


import com.market.marketJpa.repository.user.PasswordLoginRepository;
import com.market.marketJpa.repository.user.UsersRepository;
import com.market.marketJpa.service.password.generator.PasswordSaltValueGenerator;
import com.market.marketJpa.vo.user.PasswordLogin;
import com.market.marketJpa.vo.user.SignUpType;
import com.market.marketJpa.vo.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PasswordLoginServiceTest {

    @Autowired
    private PasswordLoginService passwordLoginService;
    @Autowired
    private PasswordEncryptClient passwordEncryptClient;
    @Autowired
    private PasswordLoginRepository passwordLoginRepository;
    @Autowired
    private PasswordSaltValueGenerator generator;
    @Autowired
    private UsersRepository usersRepository;

    @DisplayName("비밀번호와 유저 정보를 받으면 비밀번호 로그인 정보가 만들어진다.")
    @Test
    void signUpBy() {
        //given
        String password = "test_password";
        String email = "test@email.com";

        Users users = Users.builder()
                .email(email)
                .signUpType(SignUpType.SOCIAL)
                .build();

        Users savedUser = usersRepository.save(users);
        //when
        passwordLoginService.signUpBy(password, users);
        PasswordLogin passwordLogin = passwordLoginRepository.findByUser(users);

        //then
        assertThat(passwordLogin.getUser().getUserId()).isEqualTo(savedUser.getUserId());
    }
}
