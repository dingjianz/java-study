package com.itheima.operator;

import java.util.Scanner;

public class OperatorDemo3 {
    public static void main(String[] args) {

        int j = 10;
        boolean ff = ++j == 11;
        System.out.println("-----");
        System.out.println(ff);
        /*
            自增资减运算符
                ++：自增运算符，变量的值增加1
                --：自减运算符，变量的值减少1
         */
        int a = 10;
        System.out.println(--a); // 9
        System.out.println(a--); // 9
        System.out.println(a); // 8

        /*
         = 直接赋值
         += 加等于
         -= 减等于
         *= 乘等于
         /= 除等于
         %= 取模等于

         */
        int b = 10;
        b += 5; // b = b + 5
        System.out.println(b); // 15

        /*
        关系运算符 / 比较运算符
            > 大于
            < 小于
            >= 大于等于
            <= 小于等于
            == 等于
            != 不等于
         关系运算符的结果是一个 boolean值，true或false
         关系运算符的优先级低于算术运算符
         关系运算符的优先级高于赋值运算符
         关系运算符的优先级相同，按照从左到右的顺序进行计算
         关系运算符的结果可以直接输出，也可以赋值给一个boolean变量
         关系运算符可以进行链式比较，例如：a > b > c，但需要注意的是，链式比较的结果是一个boolean值，而不是一个数值，因此需要使用括号来明确运算顺序，例如：(a > b) &&
         */

        // 输入一个三位数，判断是否能被3整除
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        boolean isDivisibleBy3 = num % 3 == 0;
        System.out.println(num + "能否被3整除？" + isDivisibleBy3);

        /*
        逻辑运算符详解
        1. 基本逻辑运算符
            Java 中有三种基本逻辑运算符：
            & (逻辑与) - 两个操作数都为 true 时结果为 true
            | (逻辑或) - 两个操作数至少一个为 true 时结果为 true
            ! (逻辑非) - 取反，true 变 false，false 变 true
            ^ (逻辑异或) - 两个操作数不同时结果为 true

        2. 短路逻辑运算符
            && (短路与) - 功能与 & 相同，但有短路特性
            || (短路或) - 功能与 | 相同，但有短路特性

        3. 短路特性
            短路运算的核心区别：
            && (短路与) 的短路规则：
            如果左边为 false，右边表达式不会执行，直接返回 false

            如果左边为 true，才会计算右边表达式
            || (短路或) 的短路规则：
            如果左边为 true，右边表达式不会执行，直接返回 true
            如果左边为 false，才会计算右边表达式


         */

        // & (不短路) vs && (短路)
        int aa = 10;
        boolean result1 = (aa > 20) & (aa++ > 5);  // false & true = false
        // aa 的值会变成 11，因为两边都执行了

        int bb = 10;
        boolean result2 = (bb > 20) && (bb++ > 5);  // false，右边不执行
        // bb 的值还是 10，因为右边被短路了

        // | (不短路) vs || (短路)
        int c = 10;
        boolean result3 = (c > 5) | (c++ > 20);  // true | false = true
        // c 的值会变成 11，因为两边都执行了

        int d = 10;
        boolean result4 = (d > 5) || (d++ > 20);  // true，右边不执行
        // d 的值还是 10，因为右边被短路了

        // 需求：输入一个数字，判断它是否在1到10之间
        boolean result = (num > 1) && (num < 10);
        System.out.println(num + "是否在1到10之间？" + result);

        /*
        三元运算符
            语法格式：条件表达式 ? 表达式1 : 表达式2
            说明：如果条件表达式的结果为true，则执行表达式1，否则执行表达式2
         三元运算符的优先级低于关系运算符
         三元运算符的优先级低于逻辑运算符
         三元运算符的优先级高于赋值运算符
         三元运算符的结果可以直接输出，也可以赋值给一个变量
         例如：int max = (a > b) ? a : b; // 如果a大于b，则max等于a，否则max等于b
         */

        /*
        运算符的优先级
            1. 圆括号 ()
            2. 自增自减 ++ --
            3. 乘除取模 * / %
            4. 加减 + -
            5. 关系运算 > < >= <= == !=
            6. 逻辑运算 && ||
            7. 三元运算 ? :
            8. 赋值运算 = += -= *= /= %=
         */



    }
}
