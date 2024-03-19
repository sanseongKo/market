package com.market.marketJpa.repository.user;

import com.market.marketJpa.vo.user.UserProfile;
import com.market.marketJpa.vo.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByUser(Users user);
}
