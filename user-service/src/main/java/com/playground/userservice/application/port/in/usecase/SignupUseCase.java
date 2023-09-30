package com.playground.userservice.application.port.in.usecase;

import com.playground.userservice.application.port.in.usecase.dto.SignupCommand;
import com.playground.userservice.application.port.in.usecase.dto.SignupInfo;

public interface SignupUseCase {

    SignupInfo execute(SignupCommand command);

}
