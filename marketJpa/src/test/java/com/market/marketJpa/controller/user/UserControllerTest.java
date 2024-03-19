package com.market.marketJpa.controller.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.marketJpa.controller.user.request.SocialSignUpRequest;
import com.market.marketJpa.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("이메일이 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutEmail() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .name("test")
                .gu("구")
                .city("서울")
                .dong("동")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("이메일이 없습니다.")
                );

    }

    @DisplayName("이메일 형식이 아니라면 예외가 발생한다.")
    @Test
    void signUpWithNotSuitableEmailFormat() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test")
                .name("name")
                .gu("구")
                .city("서울")
                .dong("동")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("이메일 형식으로 작성해주세요.")
                );

    }

    @DisplayName("이름이 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutName() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .gu("구")
                .city("서울")
                .dong("동")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("이름이 없습니다.")
                );

    }

    @DisplayName("거주지 시가 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutCity() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .name("name")
                .gu("구")
                .dong("동")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("거주지의 시가 없습니다.")
                );

    }

    @DisplayName("거주지 구가 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutGu() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .name("name")
                .city("서울")
                .dong("동")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("거주지의 구가 없습니다.")
                );

    }

    @DisplayName("거주지 동이 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutDong() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .name("name")
                .city("서울")
                .gu("구")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("거주지의 동이 없습니다.")
                );

    }
    @DisplayName("생일이 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutBirth() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .name("name")
                .city("서울")
                .dong("동")
                .gu("구")
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("생일이 없습니다.")
                );

    }

    @DisplayName("핸드폰번호가 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutPhoneNumber() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .name("name")
                .city("서울")
                .dong("동")
                .gu("구")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("핸드폰 번호가 없습니다.")
                );

    }

    @DisplayName("핸드폰 번호의 패턴이 아니라면 예외가 발생한다.")
    @Test
    void signUpWithNoSuitablePhoneNumberFormat() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .name("name")
                .city("서울")
                .dong("동")
                .gu("구")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("01001000111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("핸드폰 번호 형식이 아닙니다.")
                );

    }

    @DisplayName("닉네임이 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutNickname() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .name("name")
                .city("서울")
                .dong("동")
                .gu("구")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .platformName("google")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("닉네임이 없습니다.")
                );

    }

    @DisplayName("소셜 로그인 플랫폼 이름이 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutPlatformName() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .name("name")
                .city("서울")
                .dong("동")
                .gu("구")
                .birth(LocalDate.now())
                .socialAccessToken("adfadsfasdfa")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("소셜 로그인 플랫폼 명이 없습니다.")
                );

    }

    @DisplayName("소셜로그인 access token이 없다면 예외가 발생한다.")
    @Test
    void signUpWithoutSocialAccessToken() throws Exception {
        //given
        SocialSignUpRequest request = SocialSignUpRequest.builder()
                .email("test@test.com")
                .name("name")
                .city("서울")
                .dong("동")
                .gu("구")
                .birth(LocalDate.now())
                .platformName("google")
                .nickname("nickname")
                .phoneNumber("010-0100-0111")
                .build();

        Map<String, String> map = objectMapper.convertValue(request, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.setAll(map);
        //when
        //then
        mockMvc.perform(multipart("/users/sign_up")
                        .params(requestMap)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.rs_code").value("BIND_001"))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()))
                .andExpect(jsonPath("$.errorMsg").value("소셜 로그인의 Access Token이 없습니다.")
                );

    }
}
