package com.market.marketJpa.service.password;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordEncryptClient {
    private static final String FORMAT = "%02x";
    private static final String ENCRYPT_ALGORITHM = "SHA-256";

    public String encryptBy(String password, String salt) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(ENCRYPT_ALGORITHM);

        md.update((password+salt).getBytes());
        byte[] pwdsalt = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : pwdsalt) {
            sb.append(String.format(FORMAT, b));
        }

        return sb.toString();
    }
}
