package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 表示这个注解只能用在方法上
@Retention(RetentionPolicy.RUNTIME) // 表示这个注解在运行时还保留
public @interface Log {
}
