package com.playground.userservice.application.port.in.usecase.dto;

public record SignInInfo(
    String accessToken,
    String refreshToken
) {

}
