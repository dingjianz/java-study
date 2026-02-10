package com.itheima.variable;

public class VariableDemo3 {
    /*
    数据类型分为两大类：基本数据类型和引用数据类型。

    1. 基本数据类型：Java提供了8种基本数据类型，分别是：
        - 整数类型：byte（1字节）、short（2字节）、int（4字节）、long（8字节）
        byte 取值范围 -128 ~ 127 内存 1字节
        short 取值范围 -32768 ~ 32767 内存 2字节
        int 取值范围 -2147483648 ~ 2147483647 内存 4字节
        long 取值范围 -9223372036854775808 ~ 9223372036854775807 内存 8字节

        - 浮点类型：float（4字节）、double（8字节）
            float 取值范围 1.4E-45 ~ 3.4028235E38 内存 4字节
            double 取值范围 4.9E-324 ~ 1.7976931348623157E308 内存 8字节

        - 字符类型：char（2字节）
        - 布尔类型：boolean（1字节，取值为true或false）

    2. 引用数据类型：引用数据类型是指对象类型，包括类、接口、数组等。引用数据类型的变量存储的是对象的引用（地址），而不是对象本身。
        - 类：类是对象的模板，定义了对象的属性和行为。例如：String类、Scanner类等。
        - 接口：接口是一个抽象类型，定义了一组方法，但没有实现。例如：Runnable接口、Serializable接口等。
        - 数组：数组是一种特殊的引用数据类型，用于存储固定大小的同类型元素的集合。例如：int[]、String[]等。
     */
    public static void main() {
        /*

            定义8种数据类型的变量
            整数类型：byte short int long
            浮点类型：float double
            字符类型：char
            布尔类型：boolean

         */

        // 定义整数类型的变量
        byte b = 100;
        short s = 10000;
        int i = 100000;
        long l = 10000000009L; // long类型的字面量需要加上L或l后缀

        // 定义浮点类型的变量
        float f = 3.14F; // float类型的字面量需要加上f或F后缀
        double d = 3.14; // double类型的字面量默认是double类型，可以直接使用

        // 定义字符类型的变量
        char c = 'A'; // 字符类型的字面量用单引号括起来
        char unicodeChar = '\u0041'; // Unicode编码表示字符A

        // 定义布尔类型的变量
        boolean boolTrue = true;
        boolean boolFalse = false;
    }
}
