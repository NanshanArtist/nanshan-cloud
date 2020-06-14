package com.lion.nanshancloudclient.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lion.nanshancloudservicedef.dubbo.DubboService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ：Lion
 * @date ：2020/3/27 2:50 下午
 * @description ：
 */
@RestController
public class DubboController {

    @Reference
    private DubboService dubboService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam String name) {
        return dubboService.getMsg();
    }
}
