package com.market.marketJpa.service.user;

import com.market.marketJpa.repository.user.UserProfileRepository;
import com.market.marketJpa.vo.user.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfile getUserProfile(UUID userId) {
        return userProfileRepository.findByUserId(userId);
    }
}
