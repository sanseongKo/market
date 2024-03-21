package com.market.marketJpa.service.user;

import com.market.marketJpa.error.BusinessException;
import com.market.marketJpa.error.ErrorCode;
import com.market.marketJpa.repository.user.PasswordLoginRepository;
import com.market.marketJpa.repository.user.SocialLoginRepository;
import com.market.marketJpa.repository.user.UserProfileRepository;
import com.market.marketJpa.repository.user.UsersRepository;
import com.market.marketJpa.service.image.ImageService;
import com.market.marketJpa.service.user.request.PasswordSignUpServiceRequest;
import com.market.marketJpa.service.user.request.SocialSignUpServiceRequest;
import com.market.marketJpa.vo.image.Image;
import com.market.marketJpa.vo.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final ImageService imageService;
    private final UserProfileRepository userProfileRepository;
    private final SocialLoginRepository socialLoginRepository;
    private final PasswordLoginRepository passwordLoginRepository;

    //TODO(전략 패턴 고려)
    @Transactional
    public UUID signUp(SocialSignUpServiceRequest request) {
        validateExistUser(request.getEmail());

        Image image = createImage(request.getImage());

        Users user = usersRepository.save(request.toUsersEntity());
        userProfileRepository.save(request.toUserProfileEntity(user, image));
        socialLoginRepository.save(request.toSocialLoginEntity(user));

        return user.getVerifiedId();
    }

    @Transactional
    public UUID signUp(PasswordSignUpServiceRequest request) {
        validateExistUser(request.getEmail());

        Image image = createImage(request.getImage());

        Users users = usersRepository.save(request.toUsersEntity());
        userProfileRepository.save(request.toUserProfileEntity(users, image));
        passwordLoginRepository.save(request.toPasswordLoginEntity(users));

        return users.getVerifiedId();
    }

    private Image createImage(MultipartFile image) {
        UUID verifyId = UUID.randomUUID();
        LocalDate now = LocalDate.now();
        return imageService.createImage(image, now, verifyId);
    }

    private void validateExistUser(String email) {
        if (usersRepository.findByEmail(email).isPresent()) {
            ErrorCode errorCode = ErrorCode.ALREADY_USER_EXIST;
            log.error(errorCode.getMessage());
            throw new BusinessException(errorCode);
        }
    }
}
