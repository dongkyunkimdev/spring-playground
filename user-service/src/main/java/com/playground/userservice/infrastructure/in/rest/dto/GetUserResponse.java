package com.playground.userservice.infrastructure.in.rest.dto;

import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record GetUserResponse(
    @Schema(description = "유저 ID", example = "1") Long userId,
    @Schema(description = "로그인 아이디", example = "admin@playground.com") String username,
    @Schema(description = "닉네임", example = "admin") String nickname,
    @Schema(description = "상태", example = "ACTIVE") UserStatus status,
    @Schema(description = "역할", example = "ADMIN") UserRole role,
    @Schema(description = "마지막 로그인 일시", example = "2023-10-02T08:54:15") LocalDateTime lastLoginAt
) {

}
