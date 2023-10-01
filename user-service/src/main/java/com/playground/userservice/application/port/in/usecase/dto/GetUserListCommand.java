package com.playground.userservice.application.port.in.usecase.dto;

import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

public record GetUserListCommand(
    String username,
    String nickname,
    List<UserStatus> statuses,
    List<UserRole> roles,
    LocalDateTime fromLastLoginAt,
    LocalDateTime toLastLoginAt
) {

}
