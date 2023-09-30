package com.playground.userservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.userservice.application.port.in.usecase.SignupUseCase;
import com.playground.userservice.application.port.in.usecase.dto.SignupCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignupInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.domain.exception.DuplicateUsernameException;
import com.playground.userservice.application.port.out.persistence.UserPersistencePort;
import com.playground.userservice.util.mapper.SignupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class SignupService implements SignupUseCase {

    private final UserPersistencePort userPersistencePort;

    private final SignupMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public SignupInfo execute(SignupCommand command) {
        if (userPersistencePort.isExistsUserByUsername(command.username())) {
            throw new DuplicateUsernameException();
        }

        User savedUser = userPersistencePort.saveUser(mapper.commandToEntity(command));

        return mapper.entityToInfo(savedUser);
    }

}
