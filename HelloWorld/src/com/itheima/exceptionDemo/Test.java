package com.itheima.exceptionDemo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    /*
    Java.lang.Throwable
        错误 Error: 代表的系统级别的错误（属于严重问题）例如：内存溢出
        系统一旦出现问题，sun公司会把这些错误封装成Error对象
        Error是给sun公司自己用的，不是给我们程序员用的
        所以我们开发人员不用管它

        异常 Exception: 代表的是程序员自己出现的问题
         分为运行时异常RuntimeException 和 编译时异常
         编译时异常和运行时异常的区别：没有继承RuntimeException的异常，直接继承于Exception。编译阶段就会错误提示。
         运行时异常：RuntimeException本身和子类，编译阶段不会错误提示，运行阶段就会错误提示。

     */

    public static void main(String[] args) throws ParseException {
        // 编译时异常（在编译阶段，必须手动处理，否则代码报错）
        String time = "2026年05月3日";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date parse = sdf.parse(time);
        System.out.println(parse);

        // 运行时异常（在运行阶段，如果代码有错误，就会抛出异常，程序就会停止）
        int[] arr = {1,2,3};
        try {
            System.out.println(2/0);
            System.out.println(arr[3]); // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException | ArithmeticException e) {
            System.out.println("e:" + e);
        }
        System.out.println("程序继续执行");


    }
}
