package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryInfo;

public interface RegisterProductCategoryUseCase {

    RegisterProductCategoryInfo execute(RegisterProductCategoryCommand command);

}
