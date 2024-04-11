package com.market.marketJpa.controller.profile;

import com.market.marketJpa.controller.profile.response.ProfileResponse;
import com.market.marketJpa.service.user.ProfileService;
import com.market.marketJpa.vo.user.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.market.marketJpa.controller.constant.UriConstantValue.USERS;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping(USERS + "/{userId}/profiles")
    public ProfileResponse getMyProfile(@PathVariable UUID userId) {
        UserProfile userProfile = profileService.getUserProfile(userId);

        return ProfileResponse.of(userProfile);
    }
}
