package com.playground.userservice.infrastructure.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
    @NotBlank(message = "username은 비어있을 수 없습니다.")
    @Email(message = "이메일 형식만 입력할 수 있습니다.")
//    @Length()
    @Size(max = 50, message = "username은 최대 50글자까지 입력할 수 있습니다.")
    @Schema(description = "로그인 아이디", example = "ddkds66@gmail.com") String username,

    @NotBlank(message = "password는 비어있을 수 없습니다.")
    @Size(max = 50, message = "password은 최대 50글자까지 입력할 수 있습니다.")
    @Schema(description = "비밀번호", example = "asdf1234!@#$") String password,

    @NotBlank(message = "nickname은 비어있을 수 없습니다.")
    @Size(max = 50, message = "nickname은 최대 50글자까지 입력할 수 있습니다.")
    @Schema(description = "닉네임", example = "mark") String nickname
) {

}
