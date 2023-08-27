package com.playground.core.exception.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private boolean success = false;
    private int status;
    private String code;
    private String reason;
    private LocalDateTime timeStamp;
    private String path;

    public ErrorResponse(ErrorReason errorReason, String path) {
        this.status = errorReason.getStatus();
        this.code = errorReason.getCode();
        this.reason = errorReason.getReason();
        this.timeStamp = LocalDateTime.now();
        this.path = path;
    }

    public ErrorResponse(int status, String code, String reason, String path) {
        this.status = status;
        this.code = code;
        this.reason = reason;
        this.timeStamp = LocalDateTime.now();
        this.path = path;
    }

}
