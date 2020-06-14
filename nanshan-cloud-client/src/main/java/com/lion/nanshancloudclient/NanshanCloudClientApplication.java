package com.lion.nanshancloudclient;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class NanshanCloudClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(NanshanCloudClientApplication.class, args);
    }

}
