package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryInfo;

public interface UpdateProductCategoryUseCase {

    UpdateProductCategoryInfo execute(UpdateProductCategoryCommand command);

}
