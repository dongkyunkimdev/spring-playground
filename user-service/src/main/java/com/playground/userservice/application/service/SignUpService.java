package com.playground.userservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.userservice.application.port.in.usecase.SignUpUseCase;
import com.playground.userservice.application.port.in.usecase.dto.SignUpCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignUpInfo;
import com.playground.userservice.application.port.out.persistence.UserPersistencePort;
import com.playground.userservice.domain.User;
import com.playground.userservice.domain.exception.DuplicateUsernameException;
import com.playground.userservice.util.mapper.SignUpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final UserPersistencePort userPersistencePort;

    private final SignUpMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public SignUpInfo execute(SignUpCommand command) {
        validateSignUp(command);

        User savedUser = saveUser(command);

        return mapper.entityToInfo(savedUser);
    }

    private void validateSignUp(SignUpCommand command) {
        if (userPersistencePort.isExistsUserByUsername(command.username())) {
            throw new DuplicateUsernameException();
        }
    }

    private User saveUser(SignUpCommand command) {
        return userPersistencePort.saveUser(mapper.commandToEntity(command));
    }

}
