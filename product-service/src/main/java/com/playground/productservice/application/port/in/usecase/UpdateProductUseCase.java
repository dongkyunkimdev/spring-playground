package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductInfo;

public interface UpdateProductUseCase {

    UpdateProductInfo execute(UpdateProductCommand command);

}
