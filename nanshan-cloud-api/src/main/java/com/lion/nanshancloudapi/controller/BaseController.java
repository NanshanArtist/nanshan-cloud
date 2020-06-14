package com.lion.nanshancloudapi.controller;

import com.lion.nanshancloudservicedef.commom.constants.FrameConstant;
import com.lion.nanshancloudservicedef.service.LambdaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private LambdaService lambdaService;

    @RequestMapping("/show")
    @ResponseBody
    public String showMsg() {
        lambdaService.optionalUse();
        return FrameConstant.START_SUCESS;
    }

    /**
     * 测试案例
     * 标注: 如果没有加 @ResponseBody 注解会默认去寻找model视图去解析获取.如果没有写jsp或其他视图则会报错
     * 在不同版本的spring要求不同, 此版本不配置视图则必须加注解
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam String name) {
        String date = DateFormat.getDateInstance().format(new Date());
        return "Today is " + date;
    }
}
