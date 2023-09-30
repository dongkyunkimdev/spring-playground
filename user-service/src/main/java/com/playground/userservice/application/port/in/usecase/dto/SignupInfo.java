package com.playground.userservice.application.port.in.usecase.dto;

public record SignupInfo(
    Long userId,
    String username,
    String nickname
) {

}
