package com.itheima.Objectdemo;

import java.util.Objects;

public class ObjectsDemo {
    public static void main(String[] args) {

        // ========== 1. requireNonNull(obj) ==========
        // 如果 obj 不为 null，直接返回该对象
        String name = "张三";
        String result = Objects.requireNonNull(name);
        System.out.println("非空对象正常返回: " + result); // 输出: 张三

        // 如果 obj 为 null，抛出 NullPointerException（默认无消息）
        try {
            String nullName = null;
            Objects.requireNonNull(nullName);
        } catch (NullPointerException e) {
            System.out.println("捕获异常: " + e.getMessage()); // 输出: null
        }

        // ========== 2. requireNonNull(obj, message) ==========
        // 如果 obj 不为 null，直接返回该对象
        String city = "北京";
        String result2 = Objects.requireNonNull(city, "城市不能为空");
        System.out.println("非空对象正常返回: " + result2); // 输出: 北京

        // 如果 obj 为 null，抛出带自定义消息的 NullPointerException
        try {
            String nullCity = null;
            Objects.requireNonNull(nullCity, "城市不能为空！");
        } catch (NullPointerException e) {
            System.out.println("捕获异常: " + e.getMessage()); // 输出: 城市不能为空！
        }

        // ========== 3. 实际开发中的典型用法：方法参数校验 ==========
        printUserInfo("李四", 25);

        try {
            printUserInfo(null, 25);
        } catch (NullPointerException e) {
            System.out.println("调用失败: " + e.getMessage());
        }
    }

    // 在方法入口处校验参数，防止空指针异常向下传播
    static void printUserInfo(String name, int age) {
        Objects.requireNonNull(name, "用户名不能为 null");
        System.out.println("用户名: " + name + ", 年龄: " + age);
    }
}
