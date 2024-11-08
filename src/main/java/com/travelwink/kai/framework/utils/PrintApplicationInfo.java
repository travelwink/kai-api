package com.travelwink.kai.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class PrintApplicationInfo {
    public static void startSuccess (ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();

        String projectName = environment.getProperty("spring.application.name");
        String projectVersion = environment.getProperty("spring.application.version");
        String contextPath = environment.getProperty("server.servlet.context-path");
        String serverPort = environment.getProperty("server.port");

        log.info("----------------------------------------------------------");
        log.info("Application {} v {} is running! Access URLs: http://127.0.0.1:{}{}", projectName, projectVersion, serverPort, contextPath);
        log.info("Swagger: http://127.0.0.1:{}{}/swagger-ui/index.html", serverPort, contextPath);
        log.info("Knife4j: http://127.0.0.1:{}{}/doc.html", serverPort, contextPath);
    }
}
