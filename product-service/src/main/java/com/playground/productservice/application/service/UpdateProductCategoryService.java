package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.UpdateProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.UpdateProductCategoryInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.DuplicateProductCategoryNameException;
import com.playground.productservice.domain.exception.ProductCategoryNotFoundException;
import com.playground.productservice.util.mapper.UpdateProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
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
        ProductCategory savedProductCategory = getProductCategory(command.productCategoryId());

        validateDuplicateName(command.name(), savedProductCategory.getName());

        savedProductCategory.updateName(command.name());

        return mapper.entityToInfo(savedProductCategory);
    }

    private ProductCategory getProductCategory(Long productCategoryId) {
        return productPersistencePort.searchProductCategoryById(productCategoryId)
            .orElseThrow(ProductCategoryNotFoundException::new);
    }

    private void validateDuplicateName(String enteredName, String savedName) {
        if (!StringUtils.containsIgnoreCase(enteredName, savedName) && productPersistencePort.isExistsProductCategoryByName(enteredName)) {
            throw new DuplicateProductCategoryNameException();
        }
    }

}
