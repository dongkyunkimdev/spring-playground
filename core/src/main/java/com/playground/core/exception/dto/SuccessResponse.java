package com.playground.core.exception.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class SuccessResponse {

    private boolean success = true;
    private int status;
    private Object data;
    private ZonedDateTime timeStamp;

    public SuccessResponse(int status, Object data) {
        this.status = status;
        this.data = data;
        this.timeStamp = ZonedDateTime.now();
    }

}
