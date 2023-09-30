package com.playground.userservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SignupResponse(
    @Schema(description = "유저 ID", example = "1") Long userId,
    @Schema(description = "로그인 아이디", example = "ddkds66@gmail.com") String username,
    @Schema(description = "닉네임", example = "mark") String nickname
) {

}
