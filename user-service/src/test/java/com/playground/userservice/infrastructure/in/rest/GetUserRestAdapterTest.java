package com.playground.userservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.ErrorResponse;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;
import com.playground.userservice.domain.exception.UserErrorCode;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserResponse;
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
class GetUserRestAdapterTest extends ControllerTest {

    @Test
    void 유저_조회_성공() throws Exception {
        // given
        final Long userId = 1L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/{userId}", userId);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        GetUserResponse userResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(userResponse.userId()).isEqualTo(userId);
        assertThat(userResponse.username()).isEqualTo("admin@playground.com");
        assertThat(userResponse.nickname()).isEqualTo("admin");
        assertThat(userResponse.status()).isEqualTo(UserStatus.ACTIVE);
        assertThat(userResponse.role()).isEqualTo(UserRole.ADMIN);
        assertThat(userResponse.lastLoginAt()).isEqualTo("2023-10-02T08:54:15");
    }

    @Test
    void 유저_조회_실패_유저가_존재하지_않음() throws Exception {
        // given
        final Long userId = 182371L;

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.POST, "/v1/users/{userId}", userId);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isNotFound());

        ErrorResponse responseDto = getErrorResponse(result);
        assertErrorResponse(responseDto, UserErrorCode.USER_NOT_FOUND);
    }

}
