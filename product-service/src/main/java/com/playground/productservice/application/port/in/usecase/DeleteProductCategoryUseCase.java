package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.DeleteProductCategoryCommand;

public interface DeleteProductCategoryUseCase {

    void execute(DeleteProductCategoryCommand command);

}
