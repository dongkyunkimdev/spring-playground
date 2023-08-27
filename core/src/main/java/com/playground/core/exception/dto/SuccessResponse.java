package com.playground.core.exception.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SuccessResponse {

    private boolean success = true;
    private int status;
    private Object data;
    private LocalDateTime timeStamp;

    public SuccessResponse(int status, Object data) {
        this.status = status;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
    }

}
