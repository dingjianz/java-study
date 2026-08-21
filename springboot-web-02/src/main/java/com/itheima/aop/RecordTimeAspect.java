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
 *
 * 通知顺序：
 * 当有多个切面的切入点都匹配到了目标方法，目标方法运行时，多个通知方法都会被执行。
 *
 * 执行顺序：
 * 1. 不同切面类中，默认按照切面类的【类名字母排序】：
 *    - 目标方法前的通知方法：字母排名靠前的先执行
 *    - 目标方法后的通知方法：字母排名靠前的后执行
 * 2. 用 @Order(数字) 加在切面类上来控制顺序：
 *    - 目标方法前的通知方法：数字小的先执行
 *    - 目标方法后的通知方法：数字小的后执行
 */
@Aspect // 表示当前类是一个 AOP 切面类
@Component // 交给 Spring IOC 容器管理
@Slf4j // Lombok 注解，自动生成日志对象 log
public class RecordTimeAspect {

    // ==================== 1. 定义可复用的切入点 ====================

    /*
     * 切入点表达式的两种常用写法：execution(...) 与 @annotation(...)
     *
     * 一、execution：根据【方法签名】匹配，最常用
     *
     * 语法：
     *   execution(访问修饰符? 返回值 包名.类名.?方法名(参数) throws 异常?)
     *   其中带 ? 的部分（访问修饰符、包名类名、throws 异常）可以省略。
     *
     * 通配符：
     *   *  单个独立的任意符号，可以通配任意返回值、包名、类名、方法名、单个参数类型
     *   .. 多个连续的任意符号，可以通配任意层级的包，或任意个数、任意类型的参数
     *
     * 示例：
     *   execution(* com.itheima.service.DeptService.list())            精确匹配无参 list 方法
     *   execution(* com.itheima.service.DeptService.delete(java.lang.Integer))
     *                                                                 参数类型要写全限定名
     *   execution(* com.itheima.service.*.delete(..))                  service 包下任意类的 delete
     *   execution(* com.itheima.service..*.*(..))                      service 包及其子包下所有方法
     *   execution(* com..DeptService.*(..))                            com 下任意层级包中的 DeptService
     *
     * 注意：
     *   - 返回值位置不能省略，任意返回值写 *
     *   - 尽量不要写 execution(* *(..)) 这类过于宽泛的表达式，会拦截到大量无关方法
     *   - 可以用 && || ! 组合多个表达式，例如：
     *       execution(* com.itheima.service.DeptService.list(..)) || execution(* com.itheima.service.DeptService.delete(..))
     *
     * 二、@annotation：根据【方法上是否标注了某个注解】匹配
     *
     * 语法：
     *   @annotation(注解的全限定名)
     *
     * 适用场景：
     *   当要匹配的方法很分散、无法用一个 execution 表达式描述时，
     *   自定义一个注解（如 @LogOperation），在需要增强的方法上标注即可。
     *
     * 示例：
     *   @annotation(com.itheima.anno.LogOperation)                     拦截标注了自定义注解的方法
     *   @annotation(org.springframework.web.bind.annotation.GetMapping) 拦截所有 GET 请求方法
     *
     * 自定义注解的写法（注意 @Retention 必须是 RUNTIME，否则运行时读不到）：
     *   @Retention(RetentionPolicy.RUNTIME)
     *   @Target(ElementType.METHOD)
     *   public @interface LogOperation { }
     *
     * 补充：如果通知方法需要拿到注解对象本身，可以把注解作为参数绑定：
     *   @Before("@annotation(logOperation)")
     *   public void before(JoinPoint jp, LogOperation logOperation) { ... }
     *
     * 三、两者对比
     *   execution：靠包名/类名/方法名的规则批量匹配，改动业务代码为零，但表达式与代码结构耦合，
     *              包结构调整后表达式可能失效
     *   @annotation：靠注解精确标记，灵活、不受包结构影响，但需要在每个目标方法上加注解
     */

    /**
     * 切入点表达式 - 匹配 service 层的所有方法
     * 使用 @Pointcut 定义后，可以在多个通知中复用
     *
     * execution 表达式拆解：
     * - *                          任意返回值
     * - com.itheima.service..      service 包及其所有子包（.. 通配多级包）
     * - *                          任意类名
     * - .*                         任意方法名
     * - (..)                       任意个数、任意类型的参数
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
     *
     * 这里没有用通配符，属于 execution 的精确匹配：
     * 返回值任意、类名和方法名写死、参数用 (..) 兼容重载
     */
    @Before("execution(* com.itheima.service.impl.DeptServiceImpl.list(..))")
    public void beforeDeptList(JoinPoint joinPoint) {
        log.info(">>> 特定方法拦截：正在查询部门列表...");
    }

    /**
     * 示例2：拦截所有返回值为 Result 的方法
     *
     * execution 中返回值位置写具体类型（全限定名）即可按返回值筛选，
     * 方法名位置的 * 表示任意包、任意类的任意方法
     */
    @AfterReturning("execution(com.itheima.pojo.Result *(..))")
    public void afterResultMethod(JoinPoint joinPoint) {
        log.info(">>> 返回值类型拦截：方法 {} 返回了 Result 对象", joinPoint.getSignature().getName());
    }

    /**
     * 示例3：使用 @annotation 形式的切入点
     * 拦截所有标注了 @GetMapping 的方法
     *
     * @annotation 内必须写注解的【全限定名】，且该注解的 @Retention 要为 RUNTIME。
     * 实际项目中更常见的做法是自定义注解（如 @LogOperation），
     * 只在需要增强的方法上标注，避免 execution 表达式与包结构强耦合。
     */
    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void beforeGetMapping(JoinPoint joinPoint) {
        log.info(">>> 注解拦截：检测到 GET 请求 - {}", joinPoint.getSignature());
    }

    /**
     * 示例4：拦截特定参数类型的方法
     * 拦截第一个参数为 Integer 的所有 service 方法
     *
     * 参数列表中 (Integer, ..) 表示：第一个参数为 Integer，后面还可以有任意个任意类型的参数。
     * 若写成 (Integer) 则只匹配"仅有一个 Integer 参数"的方法。
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
