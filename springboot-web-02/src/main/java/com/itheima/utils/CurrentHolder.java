package com.itheima.utils;

public class CurrentHolder {
    // ✅ 静态常量 - 类级别共享
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    // ✅ 静态方法 - 可以直接用类名调用
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
