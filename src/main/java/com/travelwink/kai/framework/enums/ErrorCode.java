package com.travelwink.kai.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * {@code 400xx} Bad request 扩展异常编码.
     */
    METHOD_ARGUMENT_NOT_VALID(40002, "请求参数校验异常"),

    /**
     * {@code 404xx} Not Found 扩展异常编码.
     */
    NO_RESOURCE_FOUND(40401, "请求的资源不存在"),

    // 5xx
    UNCAUGHT_EXCEPTION(50001, "程序抛出未捕获异常")

    ;
    private final int code;
    private final String desc;

}
