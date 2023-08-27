package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.DeleteProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.DeleteProductCategoryCommand;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.ProductCategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class DeleteProductCategoryService implements DeleteProductCategoryUseCase {

    private final ProductPersistencePort productPersistencePort;

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public void execute(DeleteProductCategoryCommand command) {
        ProductCategory savedProductCategory = productPersistencePort.findProductCategoryById(command.getProductCategoryId())
                .orElseThrow(ProductCategoryNotFoundException::new);

        productPersistencePort.deleteProductCategory(savedProductCategory);
    }

}
