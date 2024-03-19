package com.market.marketJpa.repository.user;

import com.market.marketJpa.vo.user.EmailAuthentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthenticationRepository extends JpaRepository<EmailAuthentication, Long> {}
