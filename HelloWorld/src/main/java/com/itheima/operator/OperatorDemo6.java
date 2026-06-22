package com.itheima.operator;

import java.util.Scanner;

public class OperatorDemo6 {
    public static void main(String[] args) {
        /*
            循环语句
                for循环
                    语法格式：
                        for(初始化语句; 条件判断语句; 步进语句) {
                            循环体语句;
                        }
                    执行流程：
                        1. 执行初始化语句
                        2. 判断条件判断语句的结果，如果为true则执行循环体语句，否则结束循环
                        3. 执行步进语句
                        4. 回到第2步继续执行
         */

        for (int i = 1; i<=9; i++) {
            // System.out.println(i);
        }

        // 1-100之间的偶数求和
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if ( i% 2 == 0) {
                sum += i;
            }
        }
        System.out.println("1-100之间的偶数求和结果为：" + sum);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个整数：");
        int num1 = sc.nextInt();
        System.out.println("请输入第二个整数：");
        int num2 = sc.nextInt();
        int count = 0;
        for (int i = Math.min(num1, num2); i <= Math.max(num1, num2); i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                count++;
            }
        }
        System.out.println("在" + num1 + "和" + num2 + "之间能被3和5都能整除的数字有：" + count + "个");
    }
}
