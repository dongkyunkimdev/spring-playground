package com.playground.core.domain.product.exception;

import com.playground.core.common.exception.BusinessException;

public class DuplicateProductCategoryNameException extends BusinessException {

    public DuplicateProductCategoryNameException(String name) {
        super("상품 카테고리 name 이 이미 존재합니다. :" + name);
    }

}
