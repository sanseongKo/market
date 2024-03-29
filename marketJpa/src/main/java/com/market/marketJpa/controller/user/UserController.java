package com.market.marketJpa.controller.user;

import com.market.marketJpa.controller.user.request.PasswordSignUpRequest;
import com.market.marketJpa.service.image.ImageService;
import com.market.marketJpa.controller.user.request.SocialSignUpRequest;
import com.market.marketJpa.service.user.UserService;
import com.market.marketJpa.vo.image.Image;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

import static com.market.marketJpa.controller.constant.UriConstantValue.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USERS)
public class UserController {

    private final UserService userService;

    @PostMapping(SOCIAL_SIGN_UP)
    public void socialSignUp(@Valid @ModelAttribute SocialSignUpRequest request) {
        userService.signUp(request.toServiceRequest());
    }

    @PostMapping(PASSWORD_SIGN_UP)
    public void passwordSignUp(@Valid @ModelAttribute PasswordSignUpRequest request) {
        userService.signUp(request.toServiceRequest());
    }
}
