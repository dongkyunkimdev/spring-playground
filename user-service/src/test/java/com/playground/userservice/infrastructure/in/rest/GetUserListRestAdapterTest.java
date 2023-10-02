package com.playground.userservice.infrastructure.in.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.playground.core.exception.dto.SuccessResponse;
import com.playground.core.paging.SliceResponse;
import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;
import com.playground.userservice.infrastructure.in.rest.dto.GetUserListResponse;
import com.playground.userservice.support.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.playground.userservice.support.AssertUtil.assertSuccessResponse;
import static com.playground.userservice.support.ControllerTestUtil.createRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ActiveProfiles("local")
class GetUserListRestAdapterTest extends ControllerTest {

    @Test
    void 유저_리스트_조회_성공() throws Exception {
        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/users");

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetUserListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });
        assertThat(sliceResponse.getContent()).hasSize(10);
        assertThat(sliceResponse.getPage()).isZero();
        assertThat(sliceResponse.getSize()).isEqualTo(10);
        assertThat(sliceResponse.isHasPrevious()).isFalse();
        assertThat(sliceResponse.isHasNext()).isTrue();

        List<GetUserListResponse> userResponseList = sliceResponse.getContent();
        userResponseList.forEach(user -> {
            assertThat(user.userId()).isNotNull();
            assertThat(user.username()).isNotNull();
            assertThat(user.nickname()).isNotNull();
            assertThat(user.status()).isNotNull();
            assertThat(user.role()).isNotNull();
            assertThat(user.lastLoginAt()).isNotNull();
            assertThat(user.createdAt()).isNotNull();
            assertThat(user.updatedAt()).isNotNull();
        });
    }

    @Test
    void 유저_리스트_조회_성공_username_필터링() throws Exception {
        // given
        final String username = "user";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/users")
            .param("username", username);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetUserListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetUserListResponse> userResponseList = sliceResponse.getContent();
        userResponseList.forEach(user -> assertThat(user.username()).containsIgnoringCase(username));
    }

    @Test
    void 유저_리스트_조회_성공_nickname_필터링() throws Exception {
        // given
        final String nickname = "user";

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/users")
            .param("nickname", nickname);

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetUserListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetUserListResponse> userResponseList = sliceResponse.getContent();
        userResponseList.forEach(user -> assertThat(user.nickname()).containsIgnoringCase(nickname));
    }

    @Test
    void 유저_리스트_조회_성공_statuses_필터링() throws Exception {
        // given
        final List<UserStatus> statuses = List.of(UserStatus.INACTIVE, UserStatus.DELETED, UserStatus.BLOCKED);

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/users")
            .param("statuses", statuses.stream().map(UserStatus::name).collect(Collectors.joining(",")));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetUserListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetUserListResponse> userResponseList = sliceResponse.getContent();
        userResponseList.forEach(user -> assertThat(user.status()).isIn(statuses));
    }

    @Test
    void 유저_리스트_조회_성공_roles_필터링() throws Exception {
        // given
        final List<UserRole> roles = List.of(UserRole.ADMIN, UserRole.SUPER_ADMIN);

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/users")
            .param("roles", roles.stream().map(UserRole::name).collect(Collectors.joining(",")));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetUserListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetUserListResponse> userResponseList = sliceResponse.getContent();
        userResponseList.forEach(user -> assertThat(user.role()).isIn(roles));
    }

    @Test
    void 유저_리스트_조회_성공_fromLastLoginAt_필터링() throws Exception {
        // given
        final LocalDateTime fromLastLoginAt = LocalDateTime.parse("2023-10-01T00:00:00");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/users")
            .param("fromLastLoginAt", String.valueOf(fromLastLoginAt));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetUserListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetUserListResponse> userResponseList = sliceResponse.getContent();
        userResponseList.forEach(user -> assertThat(user.lastLoginAt()).isAfterOrEqualTo(fromLastLoginAt));
    }

    @Test
    void 유저_리스트_조회_성공_toLastLoginAt_필터링() throws Exception {
        // given
        final LocalDateTime toLastLoginAt = LocalDateTime.parse("2023-10-31T23:59:59");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/users")
            .param("toLastLoginAt", String.valueOf(toLastLoginAt));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetUserListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetUserListResponse> userResponseList = sliceResponse.getContent();
        userResponseList.forEach(user -> assertThat(user.lastLoginAt()).isBeforeOrEqualTo(toLastLoginAt));
    }

    @Test
    void 유저_리스트_조회_성공_lastLoginAtInRange_필터링() throws Exception {
        // given
        final LocalDateTime fromLastLoginAt = LocalDateTime.parse("2023-10-01T00:00:00");
        final LocalDateTime toLastLoginAt = LocalDateTime.parse("2023-10-31T23:59:59");

        // when
        MockHttpServletRequestBuilder requestBuilder = createRequestBuilder(HttpMethod.GET, "/v1/users")
            .param("fromLastLoginAt", String.valueOf(fromLastLoginAt))
            .param("toLastLoginAt", String.valueOf(toLastLoginAt));

        ResultActions result = mvc.perform(requestBuilder);

        // then
        result.andExpect(status().isOk());

        SuccessResponse responseDto = getSuccessResponse(result);
        assertSuccessResponse(responseDto, HttpStatus.OK);

        SliceResponse<GetUserListResponse> sliceResponse = objectMapper.convertValue(responseDto.getData(), new TypeReference<>() {
        });

        List<GetUserListResponse> userResponseList = sliceResponse.getContent();
        userResponseList.forEach(user -> assertThat(user.lastLoginAt()).isBetween(fromLastLoginAt, toLastLoginAt));
    }

}
