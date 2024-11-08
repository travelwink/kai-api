package com.travelwink.kai.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 4xx
    NO_RESOURCE_FOUND(40001, "请求的资源不存在"),
    METHOD_ARGUMENT_NOT_VALID(40002, "请求参数校验异常"),

    // 5xx
    UNCAUGHT_EXCEPTION(50001, "程序抛出未捕获异常")

    ;
    private final int code;
    private final String desc;

}
