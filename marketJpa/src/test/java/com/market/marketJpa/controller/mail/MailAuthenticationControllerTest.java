package com.market.marketJpa.controller.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.marketJpa.service.mail.generator.MailAuthenticationCodeGenerator;
import com.market.marketJpa.controller.mail.request.SendAuthenticationCodeRequest;
import com.market.marketJpa.service.mail.MailSendService;
import com.market.marketJpa.service.mail.response.MailAuthenticationCodeResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = MailAuthenticationController.class)
class MailAuthenticationControllerTest {

    @MockBean
    private MailAuthenticationCodeGenerator generator;
    @MockBean
    private MailSendService mailSendService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("메일 인증 코드를 생성한다.")
    @Test
    void sendAuthenticationCode() throws Exception {
        //given
        String email = "test@test";
        String authenticationCode = generator.createAuthenticationCode();

        SendAuthenticationCodeRequest request = SendAuthenticationCodeRequest.builder()
                .email(email).build();

        when(generator.createAuthenticationCode()).thenReturn(authenticationCode);
        MailAuthenticationCodeResponse response = MailAuthenticationCodeResponse.of(email, authenticationCode);
        when(mailSendService.sendAuthenticationCodeTo(any())).thenReturn(response);

        //when
        //then
        mockMvc.perform(post("/mails/authentication_code")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
