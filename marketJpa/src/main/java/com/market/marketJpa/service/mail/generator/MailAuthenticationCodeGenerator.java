package com.market.marketJpa.service.mail.generator;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class MailAuthenticationCodeGenerator {

    private static final int RANDOM_MIN_RANGE = 100000;
    private static final int RANDOM_NEXT_INT = 900000;

    public String createAuthenticationCode() {
        SecureRandom sRandom = new SecureRandom();

        int authenticationCodeInt = RANDOM_MIN_RANGE + sRandom.nextInt(RANDOM_NEXT_INT);

        return String.valueOf(authenticationCodeInt);
    }
}
