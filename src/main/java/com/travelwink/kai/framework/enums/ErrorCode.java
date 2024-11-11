package com.travelwink.kai.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.exceptions.TooManyResultsException;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * {@code 400xx} Bad request 扩展异常编码.
     */
    METHOD_ARGUMENT_NOT_VALID(40002, "请求参数校验异常"),

    /**
     * {@code 401xx} Unauthorized 扩展异常编码.
     */
    AUTHENTICATION_FAILED(40101, "身份验证失败"),

    /**
     * {@code 404xx} Not Found 扩展异常编码.
     */
    NO_RESOURCE_FOUND(40401, "请求的资源不存在"),

    /**
     * {@code 500xx} Internal Server Error 扩展异常编码.
     */
    UNCAUGHT_EXCEPTION(50001, "程序抛出未捕获异常"),

    TOO_MANY_RESULTS_EXCEPTION(50002, "数据异常：查询到的结果过多"),

    ;
    private final int code;
    private final String desc;

}
