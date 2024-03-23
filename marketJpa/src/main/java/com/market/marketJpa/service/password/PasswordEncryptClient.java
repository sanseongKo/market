package com.market.marketJpa.service.password;

import com.market.marketJpa.error.BusinessException;
import com.market.marketJpa.error.ErrorCode;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
@Log4j2
public class PasswordEncryptClient {
    private static final String FORMAT = "%02x";
    private static final String ENCRYPT_ALGORITHM = "SHA-256";

    public String encryptBy(String password, String salt) {
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance(ENCRYPT_ALGORITHM);

            md.update((password+salt).getBytes());
            byte[] pwdsalt = md.digest();

            for (byte b : pwdsalt) {
                sb.append(String.format(FORMAT, b));
            }
        } catch (NoSuchAlgorithmException e) {
            log.error(e);
            throw new BusinessException(ErrorCode.NO_SUCH_ALGORITHM);
        }


        return sb.toString();
    }
}
