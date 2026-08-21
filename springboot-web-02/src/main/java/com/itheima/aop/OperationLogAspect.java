package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
}
