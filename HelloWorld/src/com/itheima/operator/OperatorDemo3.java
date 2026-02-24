package com.itheima.operator;

import java.util.Scanner;

public class OperatorDemo3 {
    public static void main(String[] args) {
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
        逻辑运算符
            &&：逻辑与，只有当两个条件都为true时，结果才为true，否则为false
            ||：逻辑或，只有当两个条件都为false时，结果才为false，否则为true
            !：逻辑非，取反运算符，将true变为false，将false变为true
         逻辑运算符的优先级低于关系运算符
         逻辑运算符的优先级高于赋值运算符
         逻辑运算符的优先级相同，按照从左到右的顺序进行计算
         逻辑运算符的结果是一个boolean值，true或false
         逻辑运算符可以进行短路运算，例如：a > b && c > d，如果a > b为false，那么c > d就不会被计算
         */

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
