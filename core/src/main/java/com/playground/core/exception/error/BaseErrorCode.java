package com.playground.core.exception.error;


import com.playground.core.exception.dto.ErrorReason;

public interface BaseErrorCode {

    public ErrorReason getErrorReason();

    String getExplainError() throws NoSuchFieldException;

}
