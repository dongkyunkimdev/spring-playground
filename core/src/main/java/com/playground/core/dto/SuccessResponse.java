package com.playground.core.dto;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class SuccessResponse {

    private final boolean success = true;
    private final int status;
    private final Object data;
    private final ZonedDateTime timeStamp;

    public SuccessResponse(int status, Object data) {
        this.status = status;
        this.data = data;
        this.timeStamp = ZonedDateTime.now();
    }

}
