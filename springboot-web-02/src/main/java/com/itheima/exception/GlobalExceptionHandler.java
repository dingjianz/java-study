package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.dao.DataIntegrityViolationException;
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
     * 兜底处理数据库完整性约束异常。
     * 正常情况下参数校验会提前拦截，这里作为最后防线（如绕过校验的直接写库场景）。
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleDataIntegrityViolation(DataIntegrityViolationException e) {
        String message = e.getMessage();
        if (message != null && message.contains("Data too long")) {
            return Result.error("输入内容过长，请检查后重试");
        }
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
