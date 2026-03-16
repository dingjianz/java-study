package com.itheima.variable;

import java.math.BigInteger;
import java.util.Random;
import java.util.StringJoiner;

public class BigIntegerDemo2 {
    public static void main(String[] args) {

        /*
            public BigInteger add(BigInteger val)  // 加法
            public BigInteger subtract(BigInteger val)  // 减法
            public BigInteger multiply(BigInteger val)  // 乘法
            public BigInteger divide(BigInteger val)  // 除法
            public BigInteger[] divideAndRemainder(BigInteger val)  // 除法并返回两个结果
            public BigInteger mod(BigInteger m)  // 取余
            public BigInteger pow(int exponent)  // 幂
            public BigInteger min(BigInteger val)  // 取最小值
            public BigInteger max(BigInteger val)  // 取最大值
            public BigInteger and(BigInteger val)  // 按位与
            public BigInteger or(BigInteger val)  // 按位或
            public BigInteger shiftLeft(int n)  // 左移
            public Boolean equals(Object x) // 判断是否相等
            public int IntValue() // 转换为 int
         */

        // 加法
        BigInteger bd1 = BigInteger.valueOf(10);
        BigInteger bd2 = new BigInteger("20");
        BigInteger result = bd1.add(bd2);
        System.out.println(result); // 30

        // 减法
        BigInteger bd3 = BigInteger.valueOf(10);
        BigInteger result22 = bd1.subtract(bd3);
        System.out.println(result22); // 0

        // divideAndRemainder
        BigInteger[] result33 = BigInteger.valueOf(12).divideAndRemainder(BigInteger.valueOf(5));
        System.out.println(result33[0]); // 2 商
        System.out.println(result33[1]); // 2 余数
        System.out.println(BigInteger.valueOf(12).mod(BigInteger.valueOf(5))); // 2 余数

        // 比较 equals
        System.out.println(BigInteger.valueOf(10).equals(BigInteger.valueOf(10))); // true
        System.out.println(BigInteger.valueOf(170).equals(BigInteger.valueOf(17))); // false

        // 幂
        System.out.println(BigInteger.valueOf(2).pow(3)); // 8

        // max/min 不会创建新的 BigInteger 对象
        BigInteger bd4 = bd1.max(bd2); // 20
        System.out.println(bd4 == bd1); // false
        System.out.println(bd4 == bd2); // true

        // BigInteger 转换为 int/long/double
        int i = bd1.intValue(); // 10
        long l = bd1.longValue(); // 10
        double d = bd1.doubleValue(); // 10.0
        System.out.println(d);

        // 示例 1: 基本左移
        BigInteger num1 = new BigInteger("10");
        BigInteger result1 = num1.shiftLeft(2);  // 10 << 2 = 10 * 2^2 = 10 * 4 = 40
        System.out.println("示例 1 - 基本左移:");
        System.out.println("原数：" + num1);
        System.out.println("左移 2 位：" + result1);
        System.out.println();

        // 示例 2: 大数字左移
        BigInteger num2 = new BigInteger("1000000000000");
        BigInteger result2 = num2.shiftLeft(3);  // 左移 3 位，相当于乘以 8
        System.out.println("示例 2 - 大数字左移:");
        System.out.println("原数：" + num2);
        System.out.println("左移 3 位：" + result2);
        System.out.println();

        // 示例 3: 左移 0 位
        BigInteger num3 = new BigInteger("12345");
        BigInteger result3 = num3.shiftLeft(0);  // 左移 0 位，值不变
        System.out.println("示例 3 - 左移 0 位:");
        System.out.println("原数：" + num3);
        System.out.println("左移 0 位：" + result3);
        System.out.println();

        // 示例 4: 验证左移等于乘法
        BigInteger num4 = new BigInteger("50");
        BigInteger shifted = num4.shiftLeft(4);  // 左移 4 位
        BigInteger multiplied = num4.multiply(new BigInteger("16"));  // 乘以 2^4 = 16
        System.out.println("示例 4 - 验证左移等于乘法:");
        System.out.println("原数：" + num4);
        System.out.println("左移 4 位：" + shifted);
        System.out.println("乘以 16: " + multiplied);
        System.out.println("结果相等：" + shifted.equals(multiplied));
    }
}
