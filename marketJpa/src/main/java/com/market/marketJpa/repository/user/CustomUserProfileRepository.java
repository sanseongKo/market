package com.market.marketJpa.repository.user;

import com.market.marketJpa.vo.user.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomUserProfileRepository {
    UserProfile findByUserId(UUID userId);
}
