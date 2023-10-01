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

    private final JwtProvider jwtProvider;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public SignInInfo execute(SignInCommand command) {
        User savedUser = getUser(command.username());

        validatePassword(command.password(), savedUser.getPassword());

        return generateTokens(savedUser.getUserId());
    }

    private User getUser(String username) {
        return userPersistencePort.searchUserByUsername(username)
            .orElseThrow(UserNotFoundException::new);
    }

    private void validatePassword(String enteredPassword, String savedPassword) {
        if (!passwordEncoder.matches(enteredPassword, savedPassword)) {
            throw new PasswordMismatchException();
        }
    }

    private SignInInfo generateTokens(Long userId) {
        String newAccessToken = jwtProvider.generateAccessToken(userId);
        String newRefreshToken = jwtProvider.generateRefreshToken(userId);

        return new SignInInfo(newAccessToken, newRefreshToken);
    }

}
