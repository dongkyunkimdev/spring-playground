package com.playground.userservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.userservice.domain.exception.UserErrorCode;
import com.playground.userservice.infrastructure.in.rest.dto.SignInRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignInResponse;
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
class SignInRestAdapterTest extends ControllerTest {

    @Test
    void 로그인_성공() throws Exception {
        // given
        final SignInRequest requestDto = new SignInRequest("admin@playground.com", "1234");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/signin")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SignInResponse signInResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(signInResponse.accessToken()).isNotNull();
        assertThat(signInResponse.refreshToken()).isNotNull();
    }

    @Test
    void 로그인_실패_유저가_존재하지_않음() throws Exception {
        // given
        final SignInRequest requestDto = new SignInRequest("notExist@gmail.com", "1234");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/signin")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNotFound());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, UserErrorCode.USER_NOT_FOUND);
    }

    @Test
    void 로그인_실패_비밀번호가_일치하지_않음() throws Exception {
        // given
        final SignInRequest requestDto = new SignInRequest("admin@playground.com", "12341234");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/signin")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isUnauthorized());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, UserErrorCode.PASSWORD_MISMATCH);
    }

    @Test
    void 로그인_실패_비활성화된_유저() throws Exception {
        // given
        final SignInRequest requestDto = new SignInRequest("inactiveUser@playground.com", "1234");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/signin")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isForbidden());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, UserErrorCode.USER_INACTIVE);
    }

    @Test
    void 로그인_실패_삭제된_유저() throws Exception {
        // given
        final SignInRequest requestDto = new SignInRequest("deletedUser@playground.com", "1234");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/signin")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isForbidden());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, UserErrorCode.USER_DELETED);
    }

    @Test
    void 로그인_실패_블락된_유저() throws Exception {
        // given
        final SignInRequest requestDto = new SignInRequest("blockedUser@playground.com", "1234");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/signin")
            .content(objectMapper.writeValueAsString(requestDto));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isForbidden());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, UserErrorCode.USER_BLOCKED);
    }

}
