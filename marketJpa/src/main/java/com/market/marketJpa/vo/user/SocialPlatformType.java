package com.market.marketJpa.vo.user;

import java.util.Arrays;
import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SocialPlatformType {
    GOOGLE("google"),
    NAVER("naver"),
    KAKAO("kakao");

    final private String platformName;

    static public SocialPlatformType of(String platformName) {
        return Arrays.stream(values())
                     .filter(val -> Objects.equals(platformName, val.platformName))
                     .findFirst()
                     .orElseThrow();
    }
}

