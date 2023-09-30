package com.playground.userservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.userservice.domain.exception.UserErrorCode;
import com.playground.userservice.infrastructure.in.rest.dto.SignUpRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignUpResponse;
import com.playground.userservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.playground.userservice.support.AssertUtil.assertErrorResponse;
import static com.playground.userservice.support.AssertUtil.assertSuccessResponse;
import static com.playground.userservice.support.ControllerTestUtil.createRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class SignUpRestAdapterTest extends ControllerTest {

    @Test
    void 회원가입_성공() throws Exception {
        // given
        final SignUpRequest requestDto = new SignUpRequest("ddkds66@gmail.com", "asdf1234!@#$", "mark");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/signup")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isCreated());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.CREATED);

        SignUpResponse signUpResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(signUpResponse.userId()).isNotNull();
        assertThat(signUpResponse.username()).isEqualTo(requestDto.username());
        assertThat(signUpResponse.nickname()).isEqualTo(requestDto.nickname());
    }

    @Test
    void 회원가입_실패_username이_중복됨() throws Exception {
        // given
        final SignUpRequest requestDto = new SignUpRequest("admin@playground.com", "1234", "admin");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/signup")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isConflict());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, UserErrorCode.USERNAME_DUPLICATED);
    }

}
