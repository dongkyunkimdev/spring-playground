package com.playground.productservice.application.service;

import com.playground.core.domain.product.exception.DuplicateProductCategoryNameException;
import com.playground.productservice.application.port.in.usecase.RegisterProductCategoryUseCase;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryCommand;
import com.playground.productservice.application.port.in.usecase.dto.RegisterProductCategoryInfo;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.util.mapper.RegisterProductCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterProductCategoryService implements RegisterProductCategoryUseCase {

    private final ProductPersistencePort productPersistencePort;

    private final RegisterProductCategoryMapper mapper;

    @Transactional
    @Override
    public RegisterProductCategoryInfo execute(RegisterProductCategoryCommand command) {
        if (productPersistencePort.isExistsProductCategoryByName(command.getName())) {
            throw new DuplicateProductCategoryNameException(command.getName());
        }

        var savedProductCategory = productPersistencePort.saveProductCategory(mapper.commandToEntity(command));

        return mapper.entityToInfo(savedProductCategory);
    }

}
