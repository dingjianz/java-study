package com.itheima.aop;

import cn.dev33.satoken.stp.StpUtil;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    /**
     * operate_log 表里 method_params、return_value 都是 varchar(2000)，
     * 超长会抛 Data truncation，所以入库前统一截断。
     */
    private static final int MAX_TEXT_LENGTH = 2000;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        // 构建日志体（变量名不能叫 log，会和 @Slf4j 生成的 log 字段冲突）
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(currentEmpId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(truncate(Arrays.toString(joinPoint.getArgs())));
        operateLog.setCostTime(costTime);
        operateLog.setReturnValue(truncate(result != null ? result.toString() : "void"));

        // 日志属于旁路操作，写库失败不能影响业务结果
        try {
            operateLogMapper.insert(operateLog);
        } catch (Exception e) {
            log.error("操作日志记录失败: {}.{}", operateLog.getClassName(), operateLog.getMethodName(), e);
        }

        return result;

    }

    /**
     * 取当前登录人 id。
     *
     * 注意不能用 StpUtil.getTokenInfo().loginId 再强转 Integer：
     * loginId 声明为 Object，Sa-Token 持久化会话时会把它统一转成 String，
     * 所以即使登录时传的是 Integer，取回来也是 "1" 这样的字符串，强转必抛 ClassCastException。
     * getLoginIdAsInt() 内部做了类型转换，才是正确的读法。
     */
    private Integer currentEmpId() {
        try {
            return StpUtil.getLoginIdAsInt();
        } catch (Exception e) {
            // 未登录 / 无请求上下文（如定时任务触发）时不阻断业务
            log.warn("获取当前登录用户失败，操作日志的操作人将记为空", e);
            return null;
        }
    }

    private String truncate(String text) {
        if (text == null || text.length() <= MAX_TEXT_LENGTH) {
            return text;
        }
        return text.substring(0, MAX_TEXT_LENGTH - 3) + "...";
    }
}
