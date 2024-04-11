package com.market.marketJpa.repository.user;

import com.market.marketJpa.vo.user.UserProfile;
import com.market.marketJpa.vo.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

public interface UserProfileRepository
        extends JpaRepository<UserProfile, Long>,
        CustomUserProfileRepository,
        QuerydslPredicateExecutor<UserProfile> {
    UserProfile findByUser(Users user);
}
