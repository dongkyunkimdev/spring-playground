package com.playground.productservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class ProductCategoryReferencedException extends BusinessException {

    public static final BusinessException EXCEPTION = new ProductCategoryReferencedException();

    public ProductCategoryReferencedException() {
        super(ProductErrorCode.PRODUCT_CATEGORY_REFERENCED);
    }

}
