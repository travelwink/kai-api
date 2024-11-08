package com.travelwink.kai;

import com.travelwink.kai.framework.utils.PrintApplicationInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.travelwink.kai.*.mapper")
public class KaiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =SpringApplication.run(KaiApplication.class, args);
        PrintApplicationInfo.startSuccess(context);
    }

}
