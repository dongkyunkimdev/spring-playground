package com.playground.userservice.infrastructure.out.persistence;

import com.playground.core.annotation.PersistenceAdapter;
import com.playground.userservice.application.port.out.persistence.UserPersistencePort;
import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.dao.UserRepository;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    @Override
    public boolean isExistsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
