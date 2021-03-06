package com.lion.nanshancloudservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class NanshanCloudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NanshanCloudServiceApplication.class, args);
    }

}
