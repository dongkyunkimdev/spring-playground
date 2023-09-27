package com.playground.productservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class ProductReferencedException extends BusinessException {

    public static final BusinessException EXCEPTION = new ProductReferencedException();

    public ProductReferencedException() {
        super(ProductErrorCode.PRODUCT_CATEGORY_REFERENCED);
    }

}
