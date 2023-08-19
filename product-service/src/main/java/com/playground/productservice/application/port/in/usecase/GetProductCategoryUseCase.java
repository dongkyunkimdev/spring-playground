package com.playground.productservice.application.port.in.usecase;

import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;

public interface GetProductCategoryUseCase {

    GetProductCategoryInfo execute(GetProductCategoryCommand command);

}
