package com.travelwink.kai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.travelwink.kai.*.mapper")
public class KaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaiApplication.class, args);
    }

}
