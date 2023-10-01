package com.playground.userservice.infrastructure.dao;

import com.playground.core.paging.QueryDslUtil;
import com.playground.core.paging.SliceUtil;
import com.playground.userservice.domain.QUser;
import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.dao.dto.GetUserListSearchCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.playground.userservice.infrastructure.dao.support.ConditionBuilder.*;

@Repository
@RequiredArgsConstructor
public class UserRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public Slice<User> findListBySearchCondition(GetUserListSearchCondition searchCondition, Pageable pageable) {
        List<User> content = queryFactory.selectFrom(QUser.user)
            .where(
                usernameContains(searchCondition.username()),
                nicknameContains(searchCondition.nickname()),
                statusesIn(searchCondition.statuses()),
                rolesIn(searchCondition.roles()),
                lastLoginAtInRange(searchCondition.fromLastLoginAt(), searchCondition.toLastLoginAt())
            )
            .orderBy(QueryDslUtil.createOrderSpecifiers(User.class, pageable))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize() + 1)
            .fetch();

        return SliceUtil.createSlice(content, pageable);
    }

}
