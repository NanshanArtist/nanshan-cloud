package com.lion.nanshancloudapi;

import com.lion.nanshancloudservicedef.commom.constants.FrameConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NanshanCloudApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NanshanCloudApiApplication.class, args);
        System.out.println(FrameConstant.START_SUCESS);
    }

}
