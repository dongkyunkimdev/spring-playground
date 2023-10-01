package com.playground.userservice.application.port.in.usecase.dto;

import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;

public record SignUpInfo(
    Long userId,
    String username,
    String nickname,
    UserStatus status,
    UserRole role
) {

}
