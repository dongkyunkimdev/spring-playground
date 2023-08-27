package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.GetProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.GetProductCategoryInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.ProductCategoryNotFoundException;
import com.playground.productservice.util.mapper.GetProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetProductCategoryService implements GetProductCategoryUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final GetProductCategoryMapper mapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public GetProductCategoryInfo execute(GetProductCategoryCommand command) {
        ProductCategory productCategory = productPersistencePort.findProductCategoryById(command.getProductCategoryId())
                .orElseThrow(ProductCategoryNotFoundException::new);

        return mapper.entityToInfo(productCategory);
    }

}
