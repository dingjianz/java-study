package com.itheima.operator;

public class OperatorDemo1 {
   public static void main(String[] args) {
        // 算术运算符 + - * / %

       // 整数运算
       // 细节： 整数相除的结果还是整数，也就是商
       // 其余运算和数学运算一样
       int a = 10;
       int b = 3;
       System.out.println(a + b); // 13
       System.out.println(a - b); // 7
       System.out.println(a * b); // 30
       System.out.println(a / b); // 3
       System.out.println(a % b); //

       System.out.println("-------------------------------");

       // 小数运算
       // 细节： 小数参与运算，结果有可能不准确
       double c = 10.0;
       double d = 3.0;
       System.out.println(c + d); // 13.0
       System.out.println(c - d); // 7.0
       System.out.println(c * d); // 30.0
       System.out.println(c / d); // 3.3333333333333335
       System.out.println(c % d); // 1.0


       System.out.println("-------------------------------");

       double e = 1.1;
       double f = 1.01;
       System.out.println(e + f); // 2.1100000000000003
       System.out.println(e - f); // 0.09000000000000008
       System.out.println(e * f); //  1.1110000000000002
       System.out.println(e / f); // 1.0891089108910892
       System.out.println(e % f); // 0.09000000000000008

    }
}
