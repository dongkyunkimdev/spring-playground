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

    USERNAME_DUPLICATED(HttpStatus.CONFLICT.value(), "USER_001", "중복된 username 입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "USER_002", "존재하지 않는 유저입니다."),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED.value(), "USER_003", "비밀번호가 일치하지 않습니다."),
    USER_INACTIVE(HttpStatus.FORBIDDEN.value(), "USER_004", "비활성화된 유저입니다."),
    USER_DELETED(HttpStatus.FORBIDDEN.value(), "USER_005", "삭제된 유저입니다."),
    USER_BLOCKED(HttpStatus.FORBIDDEN.value(), "USER_006", "블락된 유저입니다."),
    NICKNAME_DUPLICATED(HttpStatus.CONFLICT.value(), "USER_007", "중복된 nickname 입니다."),
    USER_PAYMENT_CARD_ALREADY_REGISTERED(HttpStatus.CONFLICT.value(), "USER_008", "이미 등록된 결제 카드 입니다.");

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
