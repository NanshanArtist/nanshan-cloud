package com.lion.nanshancloudapi.base.aop;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Lion
 * @date ：2020/3/25 8:00 下午
 * @description ：
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    /**
     * 定义切点 Pointcut
     */
    @Pointcut("execution(* com.lion.nanshancloudapi.controller..*.*(..))")
    public void executeService() {
    }

    /**
     * 定义环绕切面
     */
    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 环绕方法, 必须得有参数, 切点作为参数.
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            // 获取当前的请求(切面无法直接获取当前的请求, 只能通过上下文获取)
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            Assert.notNull(sra, "request not null");
            HttpServletRequest request = sra.getRequest();
            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String queryString = request.getQueryString();

            // 获取方法的入参
            Object[] args = pjp.getArgs();
            // 获取请求参数集合并进行遍历拼接
            StringBuilder params = new StringBuilder();
            if (args.length > 0) {
                if (HttpMethod.POST.name().equals(method)) {
                    for (Object object : args) {
                        if (!(object instanceof BindingResult)) {
                            Map<String, Object> map = getKeyAndValue(object);
                            params.append(JSON.toJSON(map).toString());
                        }
                    }
                } else if (HttpMethod.GET.name().equals(method) && StringUtils.isNotBlank(queryString)) {
                    params = new StringBuilder(URLDecoder.decode(queryString, "UTF-8"));
                }
            }

            //打印请求
            String info = "Request -> ReqUrl: " + url + ";\nIp: " + getIpAddress(request) + ";  " +
                    "Method: " + method + ";  Params: " + params;
            log.info(info);

            // result的值就是被拦截方法的返回值
            // 此处可以对这个返回值进行修改. 但是这里的return并不是返回给切点. 主要要修改对象的值才行
            return pjp.proceed();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        } finally {
            // 打印执行方法的时间
            stopWatch.stop();
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
            long totalTimeMillis = stopWatch.getTotalTimeMillis();
            log.info("{} 执行耗时：{} ms", methodName, totalTimeMillis);
        }
    }

    /***
     * 从参数中获取值
     */
    private Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>(10);
        // 得到类对象
        Class<?> userCla = obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (Field f : fs) {
            // 设置些属性是可以访问的
            f.setAccessible(true);
            Object val;
            try {
                // 得到此属性的值
                val = f.get(obj);
                // 设置值
                map.put(f.getName(), val);
            } catch (Exception e) {
                log.error("getKeyAndValue err", e);
            }
        }
        return map;
    }

    /**
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
     */
    private String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址

        String ip = request.getHeader("X-Forwarded-For");
        if (log.isDebugEnabled()) {
            log.debug("isDebugEnabledX-Forwarded-For - String ip={}", ip);
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                if (log.isDebugEnabled()) {
                    log.debug("isDebugEnabledProxy-Client-IP - String ip={}", ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (log.isDebugEnabled()) {
                    log.debug("isDebugEnabledWL-Proxy-Client-IP - String ip={}", ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                if (log.isDebugEnabled()) {
                    log.debug("isDebugEnabledHTTP_CLIENT_IP - String ip={}", ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (log.isDebugEnabled()) {
                    log.debug("isDebugEnabledHTTP_X_FORWARDED_FOR - String ip={}", ip);
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (log.isDebugEnabled()) {
                    log.debug("isDebugEnabledgetRemoteAddr - String ip={}", ip);
                }
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String s : ips) {
                if (!("unknown".equalsIgnoreCase(s))) {
                    ip = s;
                    break;
                }
            }
        }
        return ip;
    }
}
