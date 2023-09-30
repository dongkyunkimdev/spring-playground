package com.playground.userservice.application.port.in.usecase.dto;

public record SignInCommand(
    String username,
    String password
) {

}
