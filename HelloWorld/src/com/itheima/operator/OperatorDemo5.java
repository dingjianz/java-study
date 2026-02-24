package com.itheima.operator;

import java.util.Scanner;

public class OperatorDemo5 {
    public static void main(String[] args) {
        /*
            switch新特性：JDK12预览版本 JDK14正式版本
            1. 箭头语法
            2.case后后面可以是多个常量，用逗号分隔
            3. switch可以有运行结果
            4. yield关键字
             yield关键字的作用是将switch表达式的结果返回到switch表达式的位置
         */

        int num = 4;
        switch (num) {
            case 1,2 -> {
                System.out.println("星期一");
            }
            case 4 -> System.out.println("星期四");
            case 5 -> System.out.println("星期五");
            case 6 -> System.out.println("星期六");
            default -> System.out.println("输入有误");
        }

        String name = switch (num) {
            case 1,2 -> "星期一";
            case 4 -> {
                yield "星期四";
            }
            case 5 -> "星期五";
            case 6 -> "星期六";
            default -> {
                yield "输入有误";
            }
        };
        System.out.println(name);
    }
}
