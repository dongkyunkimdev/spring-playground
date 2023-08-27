package com.playground.productservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class DuplicateProductCategoryNameException extends BusinessException {

    public DuplicateProductCategoryNameException() {
        super(ProductErrorCode.PRODUCT_CATEGORY_NAME_DUPLICATED);
    }

}
