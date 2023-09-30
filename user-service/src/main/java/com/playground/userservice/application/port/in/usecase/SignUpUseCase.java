package com.playground.userservice.application.port.in.usecase;

import com.playground.userservice.application.port.in.usecase.dto.SignUpCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignUpInfo;

public interface SignUpUseCase {

    SignUpInfo execute(SignUpCommand command);

}
