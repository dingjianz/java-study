package com.itheima.method;

public class MethodDemo {
    public static void main(String[] args) {
        // 需求：定义一个方法，计算两个整数的和，并把结果打印在控制台。
        int a = 10;
        int b = 20;
        sum(a, b);
    }

    // 定义一个方法，计算两个整数的和，并把结果打印在控制台。
    // 改为 static，这样可以从 static 的 main 方法中直接调用
    public static void sum(int x, int y) {
        int result = x + y;
        System.out.println(result);
    }
}
