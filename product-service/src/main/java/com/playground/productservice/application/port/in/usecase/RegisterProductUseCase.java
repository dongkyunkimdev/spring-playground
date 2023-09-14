package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductInfo;

public interface RegisterProductUseCase {

    RegisterProductInfo execute(RegisterProductCommand command);

}
