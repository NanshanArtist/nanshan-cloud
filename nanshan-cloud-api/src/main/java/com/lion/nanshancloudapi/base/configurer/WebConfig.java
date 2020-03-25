package com.lion.nanshancloudapi.base.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author ：Lion
 * @date ：2019/10/31 11:52 上午
 * @description ：WEB的配置，替代原来的XML; Spring 1.5 前使用 extends WebMvcConfigurerAdapter 的方式
 * 之后使用 1、implements WebMvcConfigurer  2、extends WebMvcConfigurationSupport 两种方式进行
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 静态资源处理
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
    /**
     * 默认静态资源处理器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }
    /**
     * 添加 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
    /**
     * 添加 参数解析 ：例如解析User，将登录用户的信息全都放在里面
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

    }
    /**
     * 解决跨域问题
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }
    /**
     * 视图跳转控制器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}
