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
        validateProductCategoryRegister(command);

        ProductCategory savedProductCategory = saveProductCategory(command);

        return mapper.entityToInfo(savedProductCategory);
    }

    private void validateProductCategoryRegister(RegisterProductCategoryCommand command) {
        if (productPersistencePort.isExistsProductCategoryByName(command.name())) {
            throw new DuplicateProductCategoryNameException();
        }
    }

    private ProductCategory saveProductCategory(RegisterProductCategoryCommand command) {
        return productPersistencePort.saveProductCategory(mapper.commandToEntity(command));
    }

}
