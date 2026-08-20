package com.itheima.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.itheima.interceptor.DemoInterceptor;
import com.itheima.interceptor.TokenInterceptor;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private DemoInterceptor demoInterceptor;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(@Nonnull InterceptorRegistry registry) {
        // registry.addInterceptor(demoInterceptor).addPathPatterns("/**"); // 添加拦截器 拦截所有请求

        // addPathPatterns：需要拦截哪些资源
        // excludePathPatterns：排除路径
        /*
         * 拦截路径匹配规则说明
         *
         * 拦截路径          含义                      举例
         * ----------------------------------------------------------------------
         * /*              一级路径                  能匹配/depts, /emps, /login
         *                                          不能匹配 depts/1
         *
         * /**             任意级路径                能匹配/depts, /depts/1, /depts/1/2
         *
         * /depts/*        /depts下的一级路径        能匹配/depts/1
         *                                          不能匹配/depts/1/2, /depts
         *
         * /depts/**       /depts下的任意级路径      能匹配/depts, /depts/1, /depts/1/2
         *                                          不能匹配/emps/1
         */
        // registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");

         // Sa-Token 登录校验拦截器：校验失败会抛出 NotLoginException，
        // 由 GlobalExceptionHandler 统一转换成 401 响应
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/logout");
    }
}
