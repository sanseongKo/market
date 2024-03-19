package com.market.marketJpa.repository.user;

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
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @DisplayName("이메일로 유저 엔티티를 조회할 수 있다.")
    @Test
    void findByEmail() {
        //given
        String email = "test@email.com";

        Users users = Users.builder()
                .email(email)
                .signUpType(SignUpType.SOCIAL)
                .build();

        usersRepository.save(users);

        //when
        Users usersEntity = usersRepository.findByEmail(email).orElseThrow();

        //then
        assertThat(usersEntity).extracting("email", "signUpType")
                .containsExactlyInAnyOrder(email, SignUpType.SOCIAL);
    }
}
