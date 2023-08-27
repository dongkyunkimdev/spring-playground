package com.playground.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessDynamicException extends RuntimeException {

    private final int status;
    private final String code;
    private final String reason;

}
