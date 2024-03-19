package com.market.marketJpa.vo.user;

import com.market.marketJpa.vo.AuditingEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailAuthentication extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String authenticationCode;

    @Builder
    public EmailAuthentication(String email, String authenticationCode) {
        this.email = email;
        this.authenticationCode = authenticationCode;
    }
}
