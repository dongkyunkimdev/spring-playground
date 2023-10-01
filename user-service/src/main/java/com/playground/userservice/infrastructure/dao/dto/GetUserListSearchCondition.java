package com.playground.userservice.infrastructure.dao.dto;

import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.List;

public record GetUserListSearchCondition(
    String username,
    String nickname,
    List<UserStatus> statuses,
    List<UserRole> roles,
    LocalDateTime fromLastLoginAt,
    LocalDateTime toLastLoginAt
) {

}
