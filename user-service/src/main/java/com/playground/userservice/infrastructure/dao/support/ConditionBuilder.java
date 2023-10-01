package com.playground.userservice.infrastructure.dao.support;

import com.playground.userservice.domain.QUser;
import com.playground.userservice.domain.enums.UserRole;
import com.playground.userservice.domain.enums.UserStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class ConditionBuilder {

    public static BooleanExpression usernameContains(String username) {
        return StringUtils.isBlank(username) ? null : QUser.user.username.containsIgnoreCase(username);
    }

    public static BooleanExpression nicknameContains(String nickname) {
        return StringUtils.isBlank(nickname) ? null : QUser.user.nickname.containsIgnoreCase(nickname);
    }

    public static BooleanExpression statusesIn(List<UserStatus> statuses) {
        return CollectionUtils.isEmpty(statuses) ? null : QUser.user.status.in(statuses);
    }

    public static BooleanExpression rolesIn(List<UserRole> roles) {
        return CollectionUtils.isEmpty(roles) ? null : QUser.user.role.in(roles);
    }

    public static BooleanExpression lastLoginAtInRange(LocalDateTime fromLastLoginAt, LocalDateTime toLastLoginAt) {
        if (fromLastLoginAt == null && toLastLoginAt == null) {
            return null;
        }

        if (fromLastLoginAt == null) {
            return QUser.user.lastLoginAt.loe(toLastLoginAt);
        }

        if (toLastLoginAt == null) {
            return QUser.user.lastLoginAt.goe(fromLastLoginAt);
        }

        return QUser.user.lastLoginAt.between(fromLastLoginAt, toLastLoginAt);
    }

}
