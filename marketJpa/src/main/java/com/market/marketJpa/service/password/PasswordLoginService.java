package com.market.marketJpa.service.password;

import com.market.marketJpa.repository.user.PasswordLoginRepository;
import com.market.marketJpa.service.password.generator.PasswordSaltValueGenerator;
import com.market.marketJpa.vo.user.PasswordLogin;
import com.market.marketJpa.vo.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;


@Service
@RequiredArgsConstructor
public class PasswordLoginService {

    private final PasswordEncryptClient passwordEncryptClient;
    private final PasswordLoginRepository passwordLoginRepository;
    private final PasswordSaltValueGenerator generator;

    public void signUpBy(String password, Users users) throws NoSuchAlgorithmException {
        String salt = generator.getSalt();

        String encryptedPassword = passwordEncryptClient.encryptBy(password, salt);

        PasswordLogin entity = PasswordLogin.builder()
                .users(users)
                .password(encryptedPassword)
                .salt(salt)
                .build();

        passwordLoginRepository.save(entity);
    }
}
