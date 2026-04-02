package com.itheima.lambdademo;

import java.util.Arrays;
import java.util.Comparator;

public class Test1 {

    /*
        lambda 表达式是JDK8 后开始的一种新语法形式
        注意点：
         1. lambda 表达式可以用来简化匿名内部类的书写
         2. lambda 表达式只能简化函数式接口的匿名内部类的写法
         3.什么是函数式接口：
            有且仅有一个抽象方法的接口叫做函数式接口，接口上方可以加 @FunctionalInterface 注解


            1、Lambda表达式的基本作用?
            简化函数式接口的匿名内部类的写法。

            2、Lambda表达式有什么使用前提?
            必须是接口的匿名内部类，接口中只能有一个抽象方法
            3、Lambda的好处?
            Lambda是一个匿名函数，我们可以把Lambda表达式理解为是一段可以传递的代码，
            它可以写出更简洁、更灵活的代码，作为一种更紧凑的代码风格，
            使Java语言表达能力得到了提升。
     */

    public static void main(String[] args) {
        Integer[] arr = {2, 3, 1, 4, 5, 6, 7, 8, 9, 10};
        /*
            Arrays.sort(arr, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
       */

        // 初识 lambda 表达式
        Arrays.sort(arr, (o1, o2) -> o1 - o2);
        System.out.println(Arrays.toString(arr));


    }
}
