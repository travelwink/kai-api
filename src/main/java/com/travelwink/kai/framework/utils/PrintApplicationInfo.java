package com.travelwink.kai.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.*;

@Slf4j
public class PrintApplicationInfo {
    public static void startSuccess (ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();

        String projectName = environment.getProperty("spring.application.name");
        String projectVersion = environment.getProperty("spring.application.version");
        String contextPath = environment.getProperty("server.servlet.context-path");
        String serverPort = environment.getProperty("server.port");

        String startSuccess = """
                   ______   ______  ______   ______  ______     ______   __  __   ______   ______   ______   ______   ______   \s
                  /\\  ___\\ /\\__  _\\/\\  __ \\ /\\  == \\/\\__  _\\   /\\  ___\\ /\\ \\/\\ \\ /\\  ___\\ /\\  ___\\ /\\  ___\\ /\\  ___\\ /\\  ___\\  \s
                  \\ \\___  \\\\/_/\\ \\/\\ \\  __ \\\\ \\  __<\\/_/\\ \\/   \\ \\___  \\\\ \\ \\_\\ \\\\ \\ \\____\\ \\ \\____\\ \\  __\\ \\ \\___  \\\\ \\___  \\ \s
                   \\/\\_____\\  \\ \\_\\ \\ \\_\\ \\_\\\\ \\_\\ \\_\\ \\ \\_\\    \\/\\_____\\\\ \\_____\\\\ \\_____\\\\ \\_____\\\\ \\_____\\\\/\\_____\\\\/\\_____\\\s
                    \\/_____/   \\/_/  \\/_/\\/_/ \\/_/ /_/  \\/_/     \\/_____/ \\/_____/ \\/_____/ \\/_____/ \\/_____/ \\/_____/ \\/_____/\s
                """;

        System.out.println(ansi().eraseScreen().bold().fg(GREEN).a(startSuccess).reset());

        log.info("----------------------------------------------------------");
        log.info("Access URL: http://127.0.0.1:{}{}", serverPort, contextPath);
        log.info("Swagger: http://127.0.0.1:{}{}/swagger-ui/index.html", serverPort, contextPath);
        log.info("Knife4j: http://127.0.0.1:{}{}/doc.html", serverPort, contextPath);
    }
}
