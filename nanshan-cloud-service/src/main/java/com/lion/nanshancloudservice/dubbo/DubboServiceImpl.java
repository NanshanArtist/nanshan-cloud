package com.lion.nanshancloudservice.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.lion.nanshancloudservicedef.dubbo.DubboService;

/**
 * @author ：Lion
 * @date ：2020/3/27 2:34 下午
 * @description ：
 */
@Service
public class DubboServiceImpl implements DubboService {
    @Override
    public String getMsg() {
        return "msg from provider";
    }
}
