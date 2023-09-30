package com.playground.userservice.application.port.out.persistence;

import com.playground.userservice.domain.User;

public interface UserPersistencePort {

    boolean isExistsUserByUsername(String username);

    User saveUser(User user);

}
