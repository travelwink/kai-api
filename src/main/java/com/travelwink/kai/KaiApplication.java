package com.travelwink.kai;

import com.travelwink.kai.framework.utils.PrintApplicationInfo;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableDiscoveryClient
@EnableNeo4jRepositories
@SpringBootApplication
@EnableProcessApplication
@MapperScan("com.travelwink.kai.*.mapper")
public class KaiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =SpringApplication.run(KaiApplication.class, args);
        PrintApplicationInfo.startSuccess(context);
    }

}
