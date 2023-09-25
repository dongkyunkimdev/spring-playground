package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.GetProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductInfo;

public interface GetProductUseCase {

    GetProductInfo execute(GetProductCommand command);

}
