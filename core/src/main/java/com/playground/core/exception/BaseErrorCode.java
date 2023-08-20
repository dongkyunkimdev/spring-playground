package com.playground.core.exception;


import com.playground.core.dto.ErrorReason;

public interface BaseErrorCode {

    public ErrorReason getErrorReason();

    String getExplainError() throws NoSuchFieldException;

}
