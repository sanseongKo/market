package com.market.marketJpa.repository.user;

import com.market.marketJpa.vo.user.PasswordLogin;
import com.market.marketJpa.vo.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordLoginRepository extends JpaRepository<PasswordLogin, Long> {
    PasswordLogin findByUser(Users user);
}
