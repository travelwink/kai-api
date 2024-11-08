package com.travelwink.kai.framework.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.travelwink.kai.framework.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ApiResult<T> implements Serializable {

    private Integer code;

    private Boolean success;

    private String message;

    private T data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public ApiResult() {
        time  = new Date();
    }

    public static ApiResult<Boolean> result(boolean flag) {
        if (flag) {
            return ok();
        }
        return fail();
    }

    public static ApiResult<Boolean> ok() {
        return ok(null);
    }

    public static <T>ApiResult<T> ok(T data){
        return result(HttpStatus.OK ,data);
    }

    public static ApiResult<Boolean> fail() {
        return fail(ErrorCode.UNCAUGHT_EXCEPTION);
    }

    public static <T>ApiResult<T> fail(ErrorCode errorCode) {
        return fail(errorCode, null);
    }

    public static <T>ApiResult<T> fail(ErrorCode errorCode, T data) {
        return ApiResult.<T>builder()
                .code(errorCode.getCode())
                .success(false)
                .message(errorCode.getDesc())
                .data(data)
                .time(new Date())
                .build();
    }

    public static <T>ApiResult<T> result(HttpStatus status, T data) {
        return result(status, null, data);
    }

    public static <T>ApiResult<T> result(HttpStatus status, String message, T data) {
        boolean success = status.value() == 200;
        String statusMessage = status.getReasonPhrase();
        if (!StringUtils.hasText(message)) {
            message = statusMessage;
        }
        return ApiResult.<T>builder()
                .code(status.value())
                .success(success)
                .message(message)
                .data(data)
                .time(new Date())
                .build();
    }

}
