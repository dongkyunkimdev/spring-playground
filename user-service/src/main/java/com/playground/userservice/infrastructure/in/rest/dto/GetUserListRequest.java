package com.playground.userservice.infrastructure.in.rest.dto;

import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record GetUserListRequest(
    @Email(message = "이메일 형식만 입력할 수 있습니다.")
    @Size(max = 50, message = "username은 최대 50글자까지 입력할 수 있습니다.")
    @Schema(description = "로그인 아이디", example = "admin@playground.com") String username,

    @Size(max = 50, message = "nickname은 최대 50글자까지 입력할 수 있습니다.")
    @Schema(description = "닉네임", example = "admin") String nickname,

    @Schema(description = "상태", example = "ACTIVE,INACTIVE,DELETED,BLOCKED") List<UserStatus> statuses,
    @Schema(description = "역할", example = "USER,ADMIN,SUPER_ADMIN") List<UserRole> roles,
    @Schema(description = "마지막 로그인 일시 검색 시작 일시", example = "2023-10-01T00:00:00") LocalDateTime fromLastLoginAt,
    @Schema(description = "마지막 로그인 일시 검색 종료 일시", example = "2023-10-31T23:59:59") LocalDateTime toLastLoginAt
) {

}
