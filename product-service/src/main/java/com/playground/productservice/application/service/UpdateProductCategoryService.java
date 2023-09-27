package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.UpdateProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.ProductCategoryNotFoundException;
import com.playground.productservice.util.mapper.UpdateProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateProductCategoryService implements UpdateProductCategoryUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final UpdateProductCategoryMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public UpdateProductCategoryInfo execute(UpdateProductCategoryCommand command) {
        ProductCategory savedProductCategory = productPersistencePort.findProductCategoryById(command.productCategoryId())
                .orElseThrow(ProductCategoryNotFoundException::new);

        savedProductCategory.update(command);

        return mapper.entityToInfo(savedProductCategory);
    }

}
