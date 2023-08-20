package com.playground.core.domain.product.exception;

import com.playground.core.common.exception.BusinessException;

public class DuplicateProductCategoryNameException extends BusinessException {

    public DuplicateProductCategoryNameException() {
        super(ProductErrorCode.PRODUCT_CATEGORY_NAME_DUPLICATED);
    }

}
