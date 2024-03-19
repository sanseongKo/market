package com.market.marketJpa.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class DateUtil extends Date {
    public static Date now() {
        return Date.from(Instant.now());
    }
}
