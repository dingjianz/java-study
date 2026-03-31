package com.itheima.arraydemo;

public class IntegerDemo {
    static void main() {
        Integer i = new Integer(10);
        Integer i2 = Integer.valueOf(10);
        Integer i3 = Integer.valueOf("10", 2);
        System.out.println(i3); // 2

        /*
            3.这两种方式获取对象的区别(掌握)
            底层原理:

            因为在实际开发中，-128~127之间的数据，用的比较多。
            如果每次使用都是new对象，那么太浪费内存了

            所以，提前把这个范围之内的每一个数据都创建好对象
            如果要用到了不会创建新的，而是返回已经创建好的对象。
         */
        Integer i4 = Integer.valueOf(127);
        Integer i5 = Integer.valueOf(127);
        System.out.println(i4 == i5); // true

        Integer i6 = Integer.valueOf(128);
        Integer i7 = Integer.valueOf(128);
        System.out.println(i6 == i7); // false

        Integer i8 = new Integer(127);
        Integer i9 = new Integer(127);
        System.out.println(i8 == i9); // false

        Integer i10 = new Integer(128);
        Integer i11 = new Integer(128);
        System.out.println(i10 == i11); // false

        // 对象是不能直接相加减
        Integer i12 = i10 + i11;
        System.out.println(i10.intValue() + i11.intValue()); // 256
        System.out.println(i12); // 256
        System.out.println(i12 instanceof Integer); // true

        /*
            JDK5的时候提出了一个机制，自动装箱和自动拆箱
            自动装箱：把基本数据类型自动转换成对应的包装类对象
            自动拆箱：把包装类对象自动转换成对应的基本数据类型
         */

        /*
            public static String toBinaryString(int i)  得到二进制字符串
            public static String toOctalString(int i)  得到八进制字符串
            public static String toHexString(int i)  得到十六进制字符串
            public static int parseInt(String s)  把字符串转换成int
         */

        // 1. 获取二进制字符串
        String s = Integer.toBinaryString(100);
        System.out.println(s); // 1100100

        String s2 = Integer.toOctalString(100);
        System.out.println(s2); // 144

        String s3 = Integer.toHexString(100);
        System.out.println(s3); // 64

        /*
            将字符串类型的整数转成int类型的整数

            强类型语言: 每种数据在java中都有各自的数据类型
            在计算的时候，如果不是同一种数据类型，是无法直接计算的。
         */

        int i13 = Integer.parseInt("100");
        System.out.println(i13); // 100
        System.out.println(i13 + 1); // 101

        /*
        细节1:
            在类型转换的时候，括号中的参数只能是数字不能是其他，否则代码会报错
            eg： Integer.parseInt("a12"); // Exception in thread "main" java.lang.NumberFormatException: For input string: "a12"
        细节2:
            8种包装类当中，除了character都有对应的parseXxx的方法，进行类型转换
         */

        Boolean b = Boolean.parseBoolean("true");
        System.out.println(b); //  true

    }
}
