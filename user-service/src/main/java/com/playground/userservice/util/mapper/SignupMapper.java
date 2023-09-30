package com.playground.userservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.userservice.application.port.in.usecase.dto.SignupCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignupInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.util.mapper.mapstruct.SignupMapStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@CustomMapper
@RequiredArgsConstructor
public class SignupMapper {

    private final SignupMapStruct mapStruct;

    private final PasswordEncoder passwordEncoder;

    public User commandToEntity(SignupCommand command) {
        return User.builder()
            .username(command.username())
            .password(passwordEncoder.encode(command.password()))
            .nickname(command.nickname())
            .build();
    }

    public SignupInfo entityToInfo(User user) {
        return mapStruct.entityToInfo(user);
    }

}
