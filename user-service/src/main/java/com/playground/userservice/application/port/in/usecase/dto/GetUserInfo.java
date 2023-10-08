package com.playground.userservice.application.port.in.usecase.dto;

import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;

import java.time.LocalDateTime;

public record GetUserInfo(
    Long userId,
    String username,
    String nickname,
    String firstName,
    String lastName,
    UserStatus status,
    UserRole role,
    LocalDateTime lastLoginAt,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
