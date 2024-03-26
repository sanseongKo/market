package com.market.marketJpa.service.user;

import com.market.marketJpa.error.BusinessException;
import com.market.marketJpa.error.ErrorCode;
import com.market.marketJpa.repository.user.PasswordLoginRepository;
import com.market.marketJpa.repository.user.SocialLoginRepository;
import com.market.marketJpa.repository.user.UserProfileRepository;
import com.market.marketJpa.repository.user.UsersRepository;
import com.market.marketJpa.service.password.PasswordEncryptClient;
import com.market.marketJpa.service.user.request.PasswordSignUpServiceRequest;
import com.market.marketJpa.service.user.request.SocialSignUpServiceRequest;
import com.market.marketJpa.vo.user.PasswordLogin;
import com.market.marketJpa.vo.user.SocialLogin;
import com.market.marketJpa.vo.user.UserProfile;
import com.market.marketJpa.vo.user.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private SocialLoginRepository socialLoginRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordLoginRepository passwordLoginRepository;
    @Autowired
    private PasswordEncryptClient passwordEncryptClient;

    @AfterEach
    void tearDown() {
        userProfileRepository.deleteAllInBatch();
        socialLoginRepository.deleteAllInBatch();
        passwordLoginRepository.deleteAllInBatch();
        usersRepository.deleteAllInBatch();
    }

    @DisplayName("회원 가입하면 회원 정보가 저장된다.")
    @Test
    void signUp() {
        //given
        SocialSignUpServiceRequest request = createServiceRequest();

        //when
        UUID userId = userService.signUp(request);

        //then
        Users users = usersRepository.findById(userId).orElseThrow();
        SocialLogin socialLogin = socialLoginRepository.findByUser(users);
        UserProfile userProfile = userProfileRepository.findByUser(users);

        assertThat(users).extracting("userId").isEqualTo("asd@asd.com");
        assertThat(socialLogin).extracting("accessToken").isEqualTo("adfadsfasdfa");
        assertThat(userProfile).extracting("phoneNumber").isEqualTo("010-0100-0111");
    }

    @DisplayName("이미 가입한 유저 이메일이라면 가입할 수 없다.")
    @Test
    void signUpWithExistUser() {
        //given
        SocialSignUpServiceRequest request = createServiceRequest();
        userService.signUp(request);

        //when
        //then
        assertThatThrownBy(() -> userService.signUp(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage(ErrorCode.ALREADY_USER_EXIST.getMessage());
    }

    private SocialSignUpServiceRequest createServiceRequest() {
        return SocialSignUpServiceRequest.builder()
                .name("test")
                .email("asd@asd.com")
                .gu("구")
                .city("서울")
                .dong("동")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();
    }

    @DisplayName("패스워드로 회원 가입하면 회원 정보가 저장된다.")
    @Test
    void signUpWithPassword() {
        //given
        PasswordSignUpServiceRequest request = createPasswordServiceRequest();

        //when
        UUID userId = userService.signUp(request);

        Users users = usersRepository.findById(userId).orElseThrow();
        PasswordLogin passwordLogin = passwordLoginRepository.findByUser(users);
        UserProfile userProfile = userProfileRepository.findByUser(users);

        //then
        String salt = passwordLogin.getSalt();
        String encryptedPassword = passwordEncryptClient.encryptBy(request.getPassword(), salt);

        assertThat(users).extracting("email").isEqualTo("asd@asd.com");
        assertThat(passwordLogin).extracting("password").isEqualTo(encryptedPassword);
        assertThat(userProfile).extracting("phoneNumber").isEqualTo("010-0100-0111");
    }

    private PasswordSignUpServiceRequest createPasswordServiceRequest() {
        return PasswordSignUpServiceRequest.builder()
                .name("test")
                .email("asd@asd.com")
                .gu("구")
                .city("서울")
                .dong("동")
                .birth(LocalDate.now())
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .password("sadfadsf")
                .build();
    }
}
