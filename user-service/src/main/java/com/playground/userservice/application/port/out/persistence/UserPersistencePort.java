package com.playground.userservice.application.port.out.persistence;

import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.dao.dto.GetUserListSearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface UserPersistencePort {

    Slice<User> searchUserListBySearchCondition(GetUserListSearchCondition searchCondition, Pageable pageable);

    Optional<User> searchUserById(Long userId);

    Optional<User> searchUserByUsername(String username);

    boolean isExistsUserByUsername(String username);

    boolean isExistsUserByNickname(String nickname);

    User saveUser(User user);

}
