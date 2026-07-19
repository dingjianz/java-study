package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 使用 @RestControllerAdvice 统一捕获 Controller 层抛出的异常，
 * 转换成统一的 Result 错误格式返回，避免直接返回 500 及底层异常堆栈。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 @Valid 参数校验失败异常。
     * 提取第一个校验失败字段的提示信息返回（如 "部门名称长度不能超过 10 个字符"）。
     * 其中的长度限制值来自实体字段的 @Size(max=...) 注解，不需要写死。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : "参数校验失败";
        return Result.error(message);
    }

    /**
     * 处理唯一约束冲突异常（如 username 重复）。
     * DuplicateKeyException 是 DataIntegrityViolationException 的子类，
     * 需放在其之前，Spring 会优先匹配更具体的异常类型。
     * 从底层报错信息中解析出冲突的字段名，给出友好的中文提示，
     * 避免把原始 SQL 错误（如 Duplicate entry '1' for key 'emp.username'）暴露给前端。
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKey(DuplicateKeyException e) {
        String message = e.getMessage();
        if (message != null && message.contains("username")) {
            return Result.error("用户名已存在，请更换后重试");
        }
        return Result.error("数据已存在，请勿重复添加");
    }

    /**
     * 兜底处理数据库完整性约束异常。
     * 正常情况下参数校验会提前拦截，这里作为最后防线（如绕过校验的直接写库场景）。
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleDataIntegrityViolation(DataIntegrityViolationException e) {
        e.printStackTrace();  // 打印完整堆栈到服务端日志，便于排查
        String message = e.getMessage();
        if (message != null && message.contains("Data too long")) {
            return Result.error("输入内容过长，请检查后重试");
        }
        // 不把原始 SQL 错误信息返回给前端，避免暴露表结构等敏感信息
        return Result.error("数据校验失败，请检查输入内容");
    }

    /**
     * 兜底异常处理，捕获其他未被专门处理的异常。
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.error("系统异常，请联系管理员");
    }
}
