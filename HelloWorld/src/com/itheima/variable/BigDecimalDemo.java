package com.itheima.variable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class BigDecimalDemo {
    public static void main(String[] args) {
        /*
            小数点运算不精确的原因是
            1. 数字运算：类型不一样不能运算，需要转成同类型的才能计算。
            2. 运算结果不精确
            3. 运算精度不够

       构造方法获取 BigDecimal对象
            1. 构造方法
            public BigDecimal(double d)
            public BigDecimal(String s)

            2. 静态方法
            public static BigDecimal valueOf(double d)
         */

        /*
            1.通过double创建BigDecimal对象
            这种创建方式，创建的BigDecimal对象，会有误差，不建议使用
         */
        BigDecimal bd1 = new BigDecimal("0.01");
        BigDecimal bd2 = new BigDecimal("0.02");

        System.out.println(bd1); // 0.01000000000000000020816681711721685132943093776702880859375
        System.out.println(bd2); // 0200000000000000004163336342344337026588618755340576171875
        System.out.println(bd1.add(bd2)); // 03000000000000000062450045135165055398829281330108642578125


        /*
            2.通过字符串创建BigDecimal对象
            这种创建方式，创建的BigDecimal对象，不会误差
         */
        BigDecimal bd3 = new BigDecimal("0.01");
        BigDecimal bd4 = new BigDecimal("0.02");
        System.out.println(bd3); // 0.01
        System.out.println(bd4); // 0.02
        System.out.println(bd3.add(bd4)); // 0.03

        /*
            3.通过静态方法创建BigDecimal对象
            这种创建方式，创建的BigDecimal对象，不会误差

            1.如果要表示的数字不大，没有超出 double 的取值范围，建议使用静态方法
            2.如果要表示的数字比较大，超出了 double 的取值范围，建议使用构造方法
         */
        BigDecimal bd5 = BigDecimal.valueOf(0.01);
        BigDecimal bd6 = BigDecimal.valueOf(0.02);
        System.out.println(bd5); // 0.01
        System.out.println(bd6); // 0.02
        System.out.println(bd5.add(bd6)); // 0.03
        System.out.println(bd5.multiply(bd6)); // 0.0002

        // 3.如果我们传递的是0~10之间的整数，包含0，包含10，那么方法会返回已经创建好的对象，不会重新new
        System.out.println(BigDecimal.valueOf(10) == BigDecimal.valueOf(10)); // true
        System.out.println(BigDecimal.valueOf(10.0) == BigDecimal.valueOf(10.0)); // false
    }
}
