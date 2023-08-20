package com.playground.core.domain.product.exception;

import com.playground.core.common.exception.BusinessException;

public class ProductCategoryNotFoundException extends BusinessException {

    public ProductCategoryNotFoundException() {
        super(ProductErrorCode.PRODUCT_CATEGORY_NOT_FOUND);
    }

}
