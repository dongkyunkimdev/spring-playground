package com.playground.userservice.application.port.in.usecase.dto;

public record SignupCommand(
    String username,
    String password,
    String nickname
) {

}
