package com.playground.userservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.userservice.application.port.in.usecase.SignInUseCase;
import com.playground.userservice.application.port.in.usecase.dto.SignInCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignInInfo;
import com.playground.userservice.application.port.out.persistence.UserPersistencePort;
import com.playground.userservice.domain.User;
import com.playground.userservice.domain.exception.PasswordMismatchException;
import com.playground.userservice.domain.exception.UserNotFoundException;
import com.playground.userservice.infrastructure.token.JwtProvider;
import com.playground.userservice.util.mapper.SignInMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class SignInService implements SignInUseCase {

    private final UserPersistencePort userPersistencePort;

    private final PasswordEncoder passwordEncoder;

    private final SignInMapper mapper;

    private final JwtProvider jwtProvider;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public SignInInfo execute(SignInCommand command) {
        User savedUser = getUser(command);

        validateSignIn(command, savedUser);

        return generateTokens(savedUser);
    }

    private User getUser(SignInCommand command) {
        return userPersistencePort.searchUserByUsername(command.username())
            .orElseThrow(UserNotFoundException::new);
    }

    private void validateSignIn(SignInCommand command, User savedUser) {
        if (!passwordEncoder.matches(command.password(), savedUser.getPassword())) {
            throw new PasswordMismatchException();
        }
    }

    private SignInInfo generateTokens(User savedUser) {
        String newAccessToken = jwtProvider.generateAccessToken(savedUser.getUserId());
        String newRefreshToken = jwtProvider.generateRefreshToken(savedUser.getUserId());

        return new SignInInfo(newAccessToken, newRefreshToken);
    }

}
