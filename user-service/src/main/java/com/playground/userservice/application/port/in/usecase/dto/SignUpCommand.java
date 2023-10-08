package com.playground.userservice.application.port.in.usecase.dto;

public record SignUpCommand(
    String username,
    String password,
    String nickname,
    String firstName,
    String lastName
) {

}
