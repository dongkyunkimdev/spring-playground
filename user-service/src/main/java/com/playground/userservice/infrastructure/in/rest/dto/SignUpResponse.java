package com.playground.userservice.infrastructure.in.rest.dto;

import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpResponse(
    @Schema(description = "유저 ID", example = "1") Long userId,
    @Schema(description = "로그인 아이디", example = "admin@playground.com") String username,
    @Schema(description = "닉네임", example = "admin") String nickname,
    @Schema(description = "상태", example = "ACTIVE") UserStatus status,
    @Schema(description = "역할", example = "ADMIN") UserRole role
) {

}
