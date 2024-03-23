package com.market.marketJpa.vo.user;


import com.market.marketJpa.vo.AuditingEntity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.market.marketJpa.vo.constant.ConstantValue.USER_ID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends AuditingEntity {

    @Id
    private UUID userId;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private SignUpType signUpType;

    @Builder
    public Users (String email, SignUpType signUpType) {
        this.userId = UUID.randomUUID();
        this.email = email;
        this.signUpType = signUpType;
    }
}
