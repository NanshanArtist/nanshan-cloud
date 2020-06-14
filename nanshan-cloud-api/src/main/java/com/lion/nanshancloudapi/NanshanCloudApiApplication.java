package com.lion.nanshancloudapi;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.lion.nanshancloudservicedef.commom.constants.FrameConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication(scanBasePackages = "com.lion")
public class NanshanCloudApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NanshanCloudApiApplication.class, args);
        System.out.println(FrameConstant.START_SUCESS);
    }

}
