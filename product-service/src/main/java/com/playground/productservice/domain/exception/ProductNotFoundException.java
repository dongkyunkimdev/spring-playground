package com.playground.productservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {

    public static final BusinessException EXCEPTION = new ProductNotFoundException();

    public ProductNotFoundException() {
        super(ProductErrorCode.PRODUCT_NOT_FOUND);
    }

}
