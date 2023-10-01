package com.playground.userservice.application.port.out.persistence;

import com.playground.userservice.domain.User;

import java.util.Optional;

public interface UserPersistencePort {

    Optional<User> searchUserById(Long userId);

    Optional<User> searchUserByUsername(String username);

    boolean isExistsUserByUsername(String username);

    User saveUser(User user);

}
