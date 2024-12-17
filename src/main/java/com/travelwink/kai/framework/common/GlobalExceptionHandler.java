package com.travelwink.kai.framework.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.travelwink.kai.framework.enums.ErrorCode;
import com.travelwink.kai.framework.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理请求数据校验异常(400 客户端异常)
     * @param e MethodArgumentNotValidException
     * @return ApiResult
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            list.add(fieldError.getDefaultMessage());
        }
        Collections.sort(list);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            ApiResult<List<String>> result = ApiResult.fail(ErrorCode.METHOD_ARGUMENT_NOT_VALID, list);
            String resultString = objectMapper.writeValueAsString(result);
            log.warn("{} : {}", HttpStatus.BAD_REQUEST.value(), resultString);
            return result;
        } catch (JsonProcessingException exception) {
            log.error(e.getMessage());
        }
        return ApiResult.fail(ErrorCode.METHOD_ARGUMENT_NOT_VALID, list);
    }

    /**
     * 处理未经认证/登录异常(401 客户端异常)
     * @param e UnauthenticatedException
     * @return ApiResult
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResult<String> handleUnauthenticatedException(UnauthenticatedException e) {
        return ApiResult.fail(ErrorCode.UNAUTHENTICATED, e.getMessage());
    }

    /**
     * 处理身份验证失败异常(401 客户端异常)
     * @param e AuthenticationException
     * @return ApiResult
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResult<String> handleAuthenticationException(AuthenticationException e) {
        return ApiResult.fail(ErrorCode.AUTHENTICATION_FAILED, e.getMessage());
    }

    /**
     * 处理授权失败异常(403 客户端异常)
     * @param e UnauthorizedException
     * @return ApiResult
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResult<String> handleUnauthorizedException(UnauthorizedException e) {
        return ApiResult.fail(ErrorCode.UNAUTHORIZED, e.getMessage());
    }

    /**
     * 处理资源未被找到异常(404 客户端异常)
     * @param e NoResourceFoundException
     * @return ApiResult
     */
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResult<String> handleNoResourceFoundException(NoResourceFoundException e) {
        return ApiResult.fail(ErrorCode.NO_RESOURCE_FOUND, e.getMessage());
    }

    /**
     * 处理数据过多异常(500 服务器内部错误)
     * @param e TooManyResultsException
     * @return ApiResult
     */
    @ExceptionHandler(TooManyResultsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> handleTooManyResultsException(TooManyResultsException e) {
        return ApiResult.fail(ErrorCode.TOO_MANY_RESULTS_EXCEPTION, e.getMessage());
    }

    /**
     * 处理业务逻辑异常(500 服务器内部错误)
     * @param e BusinessException
     * @return ApiResult
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<String> handleBusinessException(BusinessException e) {
        return ApiResult.fail(ErrorCode.BUSINESS_EXCEPTION, e.getMessage());
    }

}
