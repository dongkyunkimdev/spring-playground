package com.playground.userservice.application.port.in.usecase;

import com.playground.userservice.application.port.in.usecase.dto.RegisterUserPaymentCardCommand;
import com.playground.userservice.application.port.in.usecase.dto.RegisterUserPaymentCardInfo;

public interface RegisterUserPaymentCardUseCase {

    RegisterUserPaymentCardInfo execute(RegisterUserPaymentCardCommand command);

}
