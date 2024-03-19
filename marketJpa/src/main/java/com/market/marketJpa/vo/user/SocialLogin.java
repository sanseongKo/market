package com.market.marketJpa.vo.user;

import com.market.marketJpa.util.DateUtil;
import com.market.marketJpa.vo.AuditingEntity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.market.marketJpa.vo.constant.ConstantValue.USER_ID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialLogin extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialLoginId;

    @Enumerated(EnumType.STRING)
    private SocialPlatformType platformType;

    private String accessToken;

    @OneToOne
    @JoinColumn(name = USER_ID)
    private Users user;

    @Builder
    public SocialLogin(String platformName, String accessToken, Users user) {
        this.platformType = SocialPlatformType.of(platformName);
        this.accessToken = accessToken;
        this.user = user;
    }
}
