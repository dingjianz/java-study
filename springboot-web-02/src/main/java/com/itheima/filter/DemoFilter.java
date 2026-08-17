package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/*
    /login 只有访问 /login 的请求才会被拦截
    /emps/* 只有访问 /emps/ 开头的请求才会被拦截
    /* 代表拦截所有请求
 */
// @WebFilter(urlPatterns = "/*") // /* 代表拦截所有请求 // 注释了 就不会拦截请求了
@Slf4j
public class DemoFilter implements Filter {
    /**
     * 初始化方法，web服务器启动的时候执行，只执行一次
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法");
    }

    /**
     * 拦截到请求之后执行，会执行多次
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求...");
        // 放行请求
        filterChain.doFilter(request, response);
    }

    /**
     * 销毁方法，web服务器关闭的时候执行。只执行一次
     */
    @Override
    public void destroy() {
        log.info("destroy 销毁方法");
    }
}
