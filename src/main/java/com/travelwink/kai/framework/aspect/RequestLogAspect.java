package com.travelwink.kai.framework.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.*;

@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    private final ObjectMapper objectMapper;

    public RequestLogAspect() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logRequest(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            Object[] args = joinPoint.getArgs();
            
            try {
                if (args != null && args.length > 0) {
                    String prettyJson = objectMapper.writeValueAsString(args[0]);
                    log.info("Controller Request - {} {}:\n{}\n", 
                            request.getMethod(),
                            request.getRequestURI(),
                            ansi().fg(GREEN).a(prettyJson).reset());
                }
            } catch (JsonProcessingException e) {
                log.error("Failed to format request JSON: {}", e.getMessage());
            }
        }
    }
}