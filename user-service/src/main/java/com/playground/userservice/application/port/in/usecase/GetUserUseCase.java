package com.playground.userservice.application.port.in.usecase;

import com.playground.userservice.application.port.in.usecase.dto.GetUserCommand;
import com.playground.userservice.application.port.in.usecase.dto.GetUserInfo;

public interface GetUserUseCase {

    GetUserInfo execute(GetUserCommand command);

}
