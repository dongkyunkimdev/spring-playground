package com.playground.userservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.userservice.application.port.in.usecase.SignUpUseCase;
import com.playground.userservice.application.port.in.usecase.dto.SignUpCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignUpInfo;
import com.playground.userservice.application.port.out.persistence.UserPersistencePort;
import com.playground.userservice.domain.User;
import com.playground.userservice.domain.exception.DuplicateNicknameException;
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
        validateDuplicateUsername(command.username());
        validateDuplicateNickname(command.nickname());

        User savedUser = saveUser(mapper.commandToEntityWithPasswordEncryption(command));

        return mapper.entityToInfo(savedUser);
    }

    private void validateDuplicateUsername(String username) {
        if (userPersistencePort.isExistsUserByUsername(username)) {
            throw new DuplicateUsernameException();
        }
    }

    private void validateDuplicateNickname(String nickname) {
        if (userPersistencePort.isExistsUserByNickname(nickname)) {
            throw new DuplicateNicknameException();
        }
    }

    private User saveUser(User user) {
        return userPersistencePort.saveUser(user);
    }

}
