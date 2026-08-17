package com.itheima.interceptor;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class DemoInterceptor implements HandlerInterceptor {

    // 在目标资源方法运行之前运行 - 返回值：true放行 / false不放行
    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) throws Exception {
        log.info("执行了DemoInterceptor的preHandle方法");
        return true;
    }

    // 在目标资源方法运行之后，视图渲染之前运行
    @Override
    public void postHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, ModelAndView modelAndView) throws Exception {
        log.info("执行了DemoInterceptor的postHandle方法");
    }

    // 在目标资源方法运行之后，视图渲染之后运行
    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, Exception ex) throws Exception {
        log.info("执行了DemoInterceptor的afterCompletion方法");
    }
}
