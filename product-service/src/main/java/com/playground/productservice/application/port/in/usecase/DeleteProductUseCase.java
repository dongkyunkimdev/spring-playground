package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.DeleteProductCommand;

public interface DeleteProductUseCase {

    void execute(DeleteProductCommand command);

}
