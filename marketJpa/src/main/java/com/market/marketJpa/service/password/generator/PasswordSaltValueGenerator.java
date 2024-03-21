package com.market.marketJpa.service.password.generator;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordSaltValueGenerator {
    private static final String FORMAT = "%02x";

    public String getSalt() {
        SecureRandom sRandom = new SecureRandom();
        byte[] salt = new byte[20];

        sRandom.nextBytes(salt);

        StringBuilder sb = new StringBuilder();

        for(byte b : salt) {
            sb.append(String.format(FORMAT, b));
        }

        return sb.toString();
    }
}
