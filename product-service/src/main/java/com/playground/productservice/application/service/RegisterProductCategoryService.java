package com.playground.productservice.application.service;

import com.playground.core.annotation.UseCase;
import com.playground.productservice.application.port.in.usecase.RegisterProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.domain.ProductCategory;
import com.playground.productservice.domain.exception.DuplicateProductCategoryNameException;
import com.playground.productservice.util.mapper.RegisterProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class RegisterProductCategoryService implements RegisterProductCategoryUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final RegisterProductCategoryMapper mapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Override
    public RegisterProductCategoryInfo execute(RegisterProductCategoryCommand command) {
        validateDuplicateName(command.name());

        ProductCategory savedProductCategory = saveProductCategory(mapper.commandToEntity(command));

        return mapper.entityToInfo(savedProductCategory);
    }

    private void validateDuplicateName(String name) {
        if (productPersistencePort.isExistsProductCategoryByName(name)) {
            throw new DuplicateProductCategoryNameException();
        }
    }

    private ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productPersistencePort.saveProductCategory(productCategory);
    }

}
