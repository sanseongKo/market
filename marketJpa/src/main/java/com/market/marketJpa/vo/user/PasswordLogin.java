package com.market.marketJpa.vo.user;

import com.market.marketJpa.vo.AuditingEntity;

import java.util.Date;

import jakarta.persistence.Entity;
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
public class PasswordLogin extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passwordLoginId;

    private String password;

    @OneToOne
    @JoinColumn(name = USER_ID)
    private Users user;

    private String salt;

    @Builder
    public PasswordLogin(String password, Users users, String salt) {
        this.password = password;
        this.user = users;
        this.salt = salt;
    }
}
