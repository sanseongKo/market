package com.market.marketJpa.repository.user;

import com.market.marketJpa.vo.user.SocialLogin;
import com.market.marketJpa.vo.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLoginRepository extends JpaRepository<SocialLogin, Long> {
    SocialLogin findByUser(Users user);
}
