package com.itheima.variable;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerDemo {
    public static void main(String[] args) {
        // 创建BigInteger对象
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            // 获取随机大整数，范围[0 ~ 2的 num次方 -1]
            // eg: 2^4 - 1 = 15 [0, 15]
            BigInteger bd1 = new BigInteger(4, random);
            System.out.println(bd1);
        }

        // 2. 获取一个指定的大证书
        // 细节：字符串中必须是整数，否则会报错

        /*
            以下都报错
            BigInteger bd22 = new BigInteger("abc");
            BigInteger bd23 = new BigInteger("1.12");
         */
        BigInteger bd2 = new BigInteger("123456789012345678901234567890");
        System.out.println(bd2);


        // 3. 获取一个指定的进制下的大整数
        /*
            注意：
                1. 字符串中的数字必须是整数
                2. 进制的范围[2 ~ 36]
                3. 字符串中的数字必须要和进制吻合
         */
        // BigInteger bd3 = new BigInteger("123", 2); // For input string: "123" under radix 2
        BigInteger bd3 = new BigInteger("123", 8);
        System.out.println(bd3); // 83 = 64 + 16 + 3


        // 4. 静态方法获取 BigInteger对象，内部有优化
        /*
            1.能表示的范围表小，在long的取值范围之内，如果超出就报错
            2.在内部对常用的数字进行了优化 -16 - 16，如果多次获取不会重新创建新的

           System.out.println(Long.MAX_VALUE); // 9223372036854775807
            System.out.println(Long.MIN_VALUE); // -9223372036854775808
         */
        BigInteger bd4 = BigInteger.valueOf(100);
        System.out.println(bd4);


        BigInteger bd5 = BigInteger.valueOf(16);
        BigInteger bd6 = BigInteger.valueOf(16);
        System.out.println(bd5 == bd6); // true

        BigInteger bd7 = BigInteger.valueOf(17);
        BigInteger bd8 = BigInteger.valueOf(17);
        System.out.println(bd7 == bd8); // false

        System.out.println(BigInteger.ZERO); // 0

        BigInteger bd9 = bd7.add(bd8);
        // 对象一旦创建，里面的数据不能发生改变，eg：bd7 bd8都没变 只是创建了新的BigInteger对象
        System.out.println(bd7); // 17
        System.out.println(bd8); // 17
        System.out.println(bd9); // 34
    }
}
