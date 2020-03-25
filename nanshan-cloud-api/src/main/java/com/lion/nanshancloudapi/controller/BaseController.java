package com.lion.nanshancloudapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author ：Lion
 * @date ：2019/10/31 8:50 下午
 * @description ：
 */
@Slf4j
@Controller
@RequestMapping("/lion")
public class BaseController {

    @Value("${app_ticket}")
    private String appTicket;

    @RequestMapping("/show")
    @ResponseBody
    public String showMsg() throws Exception {
        if (true) {
            throw new RuntimeException("some exception");
        }
        return "error";
    }

    /**
     * 测试案例
     * 标注: 如果没有加 @ResponseBody 注解会默认去寻找model视图去解析获取.如果没有写jsp或其他视图则会报错
     * 在不同版本的spring要求不同, 此版本不配置视图则必须加注解
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        String date = DateFormat.getDateInstance().format(new Date());
        log.info("test log");
        return date;
    }
}
