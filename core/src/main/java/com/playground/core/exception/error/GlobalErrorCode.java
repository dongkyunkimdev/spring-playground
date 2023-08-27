package com.playground.core.exception.error;

import com.playground.core.annotation.ExplainError;
import com.playground.core.exception.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Objects;

// TODO: Get All ErrorCode API 작성하여 클라에서 고유 에러코드를 조회할 수 있도록 함
@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "GLOBAL_001", "서버 오류. 관리자에게 문의 부탁드립니다.");

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
