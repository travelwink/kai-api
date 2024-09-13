package com.travelwink.kai;

import com.travelwink.kai.utils.PrintApplicationInfo;
import io.swagger.v3.oas.models.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import static org.springdoc.core.utils.Constants.ALL_PATTERN;

@SpringBootApplication
@MapperScan("com.travelwink.kai.*.mapper")
public class KaiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =SpringApplication.run(KaiApplication.class, args);
        PrintApplicationInfo.startSuccess(context);
    }

}
