package com.travelwink.kai.framework.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.*;

@Slf4j
@Aspect
@Component
public class ResponseLogAspect {

    private final ObjectMapper objectMapper;

    public ResponseLogAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @AfterReturning(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", returning = "result")
    public void logResponse(Object result) {
        try {
            String prettyJson = objectMapper.writeValueAsString(result);
            log.info("Controller Response:\n{}\n", ansi().fg(CYAN).a(prettyJson).reset());
        } catch (JsonProcessingException e) {
            log.error("Failed to format response JSON: {}", e.getMessage());
        }
    }
}