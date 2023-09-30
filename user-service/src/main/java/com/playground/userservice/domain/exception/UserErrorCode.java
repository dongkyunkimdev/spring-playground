package com.playground.userservice.domain.exception;

import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.dto.ErrorReason;
import com.playground.core.exception.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USERNAME_DUPLICATED(HttpStatus.CONFLICT.value(), "USER_002", "중복된 username 입니다.");

    private final int status;
    private final String code;
    private final String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().status(status).code(code).reason(reason).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);

        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }

}
