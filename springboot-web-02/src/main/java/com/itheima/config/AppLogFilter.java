package com.itheima.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * 自定义日志过滤器：只记录应用主动的日志，过滤框架启动信息
 */
public class AppLogFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        String message = event.getFormattedMessage();

        // 过滤掉 Spring Boot 框架的启动信息
        if (message.contains("Starting ") ||
            message.contains("Started ") ||
            message.contains("Running with Spring Boot") ||
            message.contains("No active profile set") ||
            message.contains("HV000001:") ||
            message.contains("Logging initialized")) {
            return FilterReply.DENY;
        }

        return FilterReply.ACCEPT;
    }
}
