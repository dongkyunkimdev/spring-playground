package com.playground.userservice.application.port.in.usecase;

import com.playground.core.annotation.UseCase;
import com.playground.userservice.application.port.in.usecase.dto.GetUserCommand;
import com.playground.userservice.application.port.in.usecase.dto.GetUserInfo;
import com.playground.userservice.application.port.out.persistence.UserPersistencePort;
import com.playground.userservice.domain.User;
import com.playground.userservice.domain.exception.UserNotFoundException;
import com.playground.userservice.util.mapper.GetUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {

    private final UserPersistencePort userPersistencePort;

    private final GetUserMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public GetUserInfo execute(GetUserCommand command) {
        User savedUser = getUser(command.userId());

        return mapper.entityToInfo(savedUser);
    }

    private User getUser(Long userId) {
        return userPersistencePort.searchUserById(userId)
            .orElseThrow(UserNotFoundException::new);
    }

}
