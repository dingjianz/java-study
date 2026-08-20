package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * AOP 切面类 - 演示各种通知类型和 JoinPoint 的使用
 *
 * AOP (Aspect Oriented Programming) 面向切面编程：
 * - 在不修改原始代码的情况下，对方法进行功能增强
 * - 解耦业务逻辑与横切关注点（日志、性能监控、事务管理等）
 *
 * 核心概念：
 * - 切面 (Aspect)：@Aspect 注解标识的类，封装横切逻辑
 * - 连接点 (JoinPoint)：可以被拦截的方法执行点
 * - 切入点 (Pointcut)：通过表达式匹配要拦截的方法
 * - 通知 (Advice)：在切入点执行的增强逻辑
 *
 * 五种通知类型（执行顺序）：
 * 1. @Around 前半部分
 * 2. @Before - 前置通知：在目标方法执行前运行
 * 3. 目标方法执行
 * 4. @AfterReturning - 返回后通知：在目标方法成功返回后运行
 * 5. @AfterThrowing - 异常通知：在目标方法抛出异常后运行
 * 6. @After - 后置通知：在目标方法执行后运行（无论是否异常）
 * 7. @Around 后半部分
 */
@Aspect // 表示当前类是一个 AOP 切面类
@Component // 交给 Spring IOC 容器管理
@Slf4j // Lombok 注解，自动生成日志对象 log
public class RecordTimeAspect {

    // ==================== 1. 定义可复用的切入点 ====================

    /**
     * 切入点表达式 - 匹配 service 层的所有方法
     * 使用 @Pointcut 定义后，可以在多个通知中复用
     */
    @Pointcut("execution(* com.itheima.service..*.*(..))")
    public void servicePointcut() {
        // 切入点方法体为空，仅作为标识
    }

    /**
     * 切入点表达式 - 匹配 controller 层的所有方法
     */
    @Pointcut("execution(* com.itheima.controller..*.*(..))")
    public void controllerPointcut() {
    }

    // ==================== 2. 前置通知 @Before ====================

    /**
     * 前置通知 - 在目标方法执行前执行
     * 演示 JoinPoint 的使用：获取方法签名、参数等信息
     *
     * @param joinPoint 连接点对象，包含目标方法的信息
     */
    @Before("servicePointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName(); // 方法名
        String className = joinPoint.getTarget().getClass().getSimpleName(); // 类名

        // 获取方法参数
        Object[] args = joinPoint.getArgs();

        log.info("========== 前置通知 @Before ==========");
        log.info("目标类: {}", className);
        log.info("目标方法: {}", methodName);
        log.info("方法参数: {}", Arrays.toString(args));
        log.info("方法参数个数: {}", args.length);
    }

    // ==================== 3. 后置通知 @After ====================

    /**
     * 后置通知 - 在目标方法执行后执行（无论是否异常都会执行）
     * 相当于 finally 块
     * 注意：无法获取方法返回值
     *
     * @param joinPoint 连接点对象
     */
    @After("servicePointcut()")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("========== 后置通知 @After ==========");
        log.info("方法 {} 执行完毕（无论成功还是异常）", joinPoint.getSignature().getName());
    }

    // ==================== 4. 返回后通知 @AfterReturning ====================

    /**
     * 返回后通知 - 在目标方法正常返回后执行
     * 可以获取方法的返回值
     * 如果方法抛出异常，此通知不会执行
     *
     * @param joinPoint 连接点对象
     * @param result    目标方法的返回值（通过 returning 属性指定参数名）
     */
    @AfterReturning(pointcut = "servicePointcut()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        log.info("========== 返回后通知 @AfterReturning ==========");
        log.info("方法 {} 正常返回", joinPoint.getSignature().getName());
        log.info("返回值类型: {}", result != null ? result.getClass().getSimpleName() : "null");
        log.info("返回值内容: {}", result);
    }

    // ==================== 5. 异常通知 @AfterThrowing ====================

    /**
     * 异常通知 - 在目标方法抛出异常后执行
     * 可以获取异常信息
     * 如果方法正常返回，此通知不会执行
     *
     * @param joinPoint 连接点对象
     * @param ex        目标方法抛出的异常（通过 throwing 属性指定参数名）
     */
    @AfterThrowing(pointcut = "servicePointcut()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        log.error("========== 异常通知 @AfterThrowing ==========");
        log.error("方法 {} 抛出异常", joinPoint.getSignature().getName());
        log.error("异常类型: {}", ex.getClass().getSimpleName());
        log.error("异常信息: {}", ex.getMessage());
        log.error("异常堆栈: ", ex);
    }

    // ==================== 6. 环绕通知 @Around ====================

    /**
     * 环绕通知 - 最强大的通知类型
     * 可以在目标方法执行前后进行增强，甚至可以阻止目标方法的执行
     * 必须使用 ProceedingJoinPoint（JoinPoint 的子接口）
     *
     * 演示功能：记录 controller 层方法的执行时间
     *
     * @param pjp ProceedingJoinPoint 连接点对象（环绕通知专用）
     * @return 目标方法的返回值
     * @throws Throwable 目标方法可能抛出的异常
     */
    @Around("controllerPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        // ProceedingJoinPoint 是 JoinPoint 的子接口，只能用在 @Around 中
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        log.info("========== 环绕通知 @Around - 前置增强 ==========");
        log.info("即将执行: {}.{}", className, methodName);
        log.info("请求参数: {}", Arrays.toString(args));

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        Object result = null;
        try {
            // 执行目标方法（必须调用此方法，否则目标方法不会执行）
            result = pjp.proceed();

            // 记录结束时间
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            log.info("========== 环绕通知 @Around - 后置增强 ==========");
            log.info("方法 {}.{} 执行成功", className, methodName);
            log.info("执行耗时: {}ms", duration);
            log.info("返回结果: {}", result);

        } catch (Throwable throwable) {
            log.error("========== 环绕通知 @Around - 异常处理 ==========");
            log.error("方法执行出错: {}", throwable.getMessage());
            // 可以在这里进行异常处理、日志记录等
            throw throwable; // 重新抛出异常，让全局异常处理器处理
        }

        return result;
    }

    // ==================== 7. 更精确的切入点表达式示例 ====================

    /**
     * 示例1：只拦截特定类的特定方法
     * 拦截 DeptServiceImpl 的 list 方法
     */
    @Before("execution(* com.itheima.service.impl.DeptServiceImpl.list(..))")
    public void beforeDeptList(JoinPoint joinPoint) {
        log.info(">>> 特定方法拦截：正在查询部门列表...");
    }

    /**
     * 示例2：拦截所有返回值为 Result 的方法
     */
    @AfterReturning("execution(com.itheima.pojo.Result *(..))")
    public void afterResultMethod(JoinPoint joinPoint) {
        log.info(">>> 返回值类型拦截：方法 {} 返回了 Result 对象", joinPoint.getSignature().getName());
    }

    /**
     * 示例3：使用注解形式的切入点
     * 拦截所有标注了 @GetMapping 的方法
     */
    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void beforeGetMapping(JoinPoint joinPoint) {
        log.info(">>> 注解拦截：检测到 GET 请求 - {}", joinPoint.getSignature());
    }

    /**
     * 示例4：拦截特定参数类型的方法
     * 拦截第一个参数为 Integer 的所有 service 方法
     */
    @Before("execution(* com.itheima.service..*.*(Integer, ..))")
    public void beforeIntegerParam(JoinPoint joinPoint) {
        log.info(">>> 参数类型拦截：检测到第一个参数为 Integer 的方法调用");
    }

    // ==================== 8. JoinPoint 详细用法演示 ====================

    /**
     * 演示 JoinPoint 的各种方法
     */
    @Before("execution(* com.itheima.service.impl.EmpServiceImpl.update(..))")
    public void demonstrateJoinPoint(JoinPoint joinPoint) {
        log.info("========== JoinPoint 详细用法演示 ==========");

        // 1. 获取签名信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("方法名: {}", signature.getName());
        log.info("声明类型: {}", signature.getDeclaringTypeName());
        log.info("返回值类型: {}", signature.getReturnType().getSimpleName());

        // 2. 获取目标对象信息
        log.info("目标对象: {}", joinPoint.getTarget());
        log.info("目标类: {}", joinPoint.getTarget().getClass().getName());

        // 3. 获取方法参数
        Object[] args = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();
        Class<?>[] paramTypes = signature.getParameterTypes();

        log.info("参数个数: {}", args.length);
        for (int i = 0; i < args.length; i++) {
            log.info("参数[{}] - 名称: {}, 类型: {}, 值: {}",
                    i, paramNames[i], paramTypes[i].getSimpleName(), args[i]);
        }

        // 4. 获取连接点类型
        log.info("连接点类型: {}", joinPoint.getKind());
    }
}
