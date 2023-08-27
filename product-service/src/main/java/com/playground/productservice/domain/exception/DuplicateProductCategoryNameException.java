package com.playground.productservice.domain.exception;

import com.playground.core.exception.BusinessException;

public class DuplicateProductCategoryNameException extends BusinessException {

    public static final BusinessException EXCEPTION = new DuplicateProductCategoryNameException();

    public DuplicateProductCategoryNameException() {
        super(ProductErrorCode.PRODUCT_CATEGORY_NAME_DUPLICATED);
    }

}
