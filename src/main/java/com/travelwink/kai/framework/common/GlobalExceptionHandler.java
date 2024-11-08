package com.travelwink.kai.framework.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.travelwink.kai.framework.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
     * 处理请求数据校验异常
     * @param e MethodArgumentNotValidException
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
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
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException exception) {
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理资源未被找到异常
     * @param e MethodArgumentNotValidException
     * @return ResponseEntity
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResult<String>> handleNoResourceFoundException(NoResourceFoundException e) {
        ApiResult<String> result = ApiResult.fail(ErrorCode.NO_RESOURCE_FOUND, e.getMessage());
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
