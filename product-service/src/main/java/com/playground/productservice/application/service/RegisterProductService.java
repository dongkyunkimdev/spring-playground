package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.RegisterProductUseCase;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.Product;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.ProductCategoryNotFoundException;
import com.playground.productservice.util.mapper.RegisterProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class RegisterProductService implements RegisterProductUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final RegisterProductMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public RegisterProductInfo execute(RegisterProductCommand command) {
        ProductCategory savedProductCategory = getProductCategory(command);

        Product savedProduct = saveProduct(command, savedProductCategory);

        return mapper.entityToInfo(savedProduct);
    }

    private Product saveProduct(RegisterProductCommand command, ProductCategory savedProductCategory) {
        return productPersistencePort.saveProduct(mapper.commandToEntity(command, savedProductCategory));
    }

    private ProductCategory getProductCategory(RegisterProductCommand command) {
        return productPersistencePort.searchProductCategoryById(command.productCategoryId())
            .orElseThrow(ProductCategoryNotFoundException::new);
    }

}
