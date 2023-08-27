package com.playground.productservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class ProductCategoryNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new ProductCategoryNotFoundException();

    public ProductCategoryNotFoundException() {
        super(ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND);
    }

}
