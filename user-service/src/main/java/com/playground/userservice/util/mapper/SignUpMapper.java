package com.playground.userservice.util.mapper;

import com.playground.core.annotation.CustomMapper;
import com.playground.userservice.application.port.in.usecase.dto.SignUpCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignUpInfo;
import com.playground.userservice.domain.User;
import com.playground.userservice.infrastructure.in.rest.dto.SignUpRequest;
import com.playground.userservice.infrastructure.in.rest.dto.SignUpResponse;
import com.playground.userservice.util.mapper.mapstruct.SignUpMapStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@CustomMapper
@RequiredArgsConstructor
public class SignUpMapper {

    private final SignUpMapStruct mapStruct;

    private final PasswordEncoder passwordEncoder;

    public SignUpCommand requestToCommand(SignUpRequest request) {
        return mapStruct.requestToCommand(request);
    }

    public User commandToEntityWithPasswordEncryption(SignUpCommand command) {
        return User.builder()
            .username(command.username())
            .password(passwordEncoder.encode(command.password()))
            .nickname(command.nickname())
            .build();
    }

    public SignUpInfo entityToInfo(User user) {
        return mapStruct.entityToInfo(user);
    }

    public SignUpResponse infoToResponse(SignUpInfo info) {
        return mapStruct.infoToResponse(info);
    }

}
