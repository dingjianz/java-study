package com.itheima.literal;

public class LiteralDemo1 {
    public  static void main(String[] args) {
        /*
        1. 什么是字面量？
            字面量是程序中直接写出的固定值，例如数字、字符、字符串等。
        2. 字面量的分类：
            - 整数类型的字面量：可以是十进制、八进制、二进制或十六进制的整数。例如：10（十进制）、010（八进制）、0b1010（二进制）、0xA（十六进制）。
            - 浮点类型的字面量：可以是默认的double类型的浮点数，也可以是float类型的浮点数。例如：3.14（double类型）、3.14f（float类型）。
            - 字符类型的字面量：用单引号括起来的单个字符。例如：'A'、'\u0041'（Unicode编码表示字符A）。
            - 布尔类型的字面量：true和false。
            - 字符串类型的字面量：用双引号括起来的字符序列。例如："Hello, World!"。
        * */
        // 整数类型的字面量
        System.out.println(10);      // 十进制
        System.out.println(010);     // 八进制
        System.out.println(0b1010);  // 二进制
        System.out.println(0xA);     // 十六进制

        // 浮点类型的字面量
        System.out.println(3.14);    // 默认是double类型
        System.out.println(3.14f);   // float类型

        // 字符类型的字面量
        System.out.println('A');
        System.out.println('\u0041'); // Unicode编码表示字符A

        // 布尔类型的字面量
        System.out.println(true);
        System.out.println(false);

        // 字符串类型的字面量
        System.out.println("Hello, World!");
    }
}
