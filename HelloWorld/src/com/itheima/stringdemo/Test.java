package com.itheima.stringdemo;

public class Test {
    static void main() {
        /*
            第一种：直接复制
            第二种： new 关键字
                public String()  空白字符串，不含任何内容
                public String(String original)  根据传入的字符串，创建新的字符串对象
                public String(char[] chs)  根据字符数组，创建字符串对象
                public String(byte[] bts) 根据字节数组，创建字符串对象
         */

        // 1.直接赋值
        String s = "abc";

        // 2.new + 构造方法的方式创建字符串对象

        // 2.1 new + 空参构造
        String s1 = new String(); // 不推荐
        String s2 = ""; // 推荐

        // 2.2 new + 有参构造
        // 根据传入字符串的内容，创建一个新的字符串对象
        String s3 = new String(s);

        // 2.3 new + 有参构造（字符数组）
        char[] chs = {'a', 'b', 'c', 'd', 'e'};
        String s4 = new String(chs); // "abcde"

        // 2.4 new + 有参构造(字节数组)
        // ASCII 码表 97--a  98--b ....
        byte[] bts = {97, 98, 99, 100, 101};
        String s5 = new String(bts); // abcde
        System.out.println(s5);

        // 字符比较
        /*
            基本数据类型比较的是数据值
            引用数据类型比较的是地址值
         */
        System.out.println(s == s3); // false
        System.out.println(s.equals(s3)); // 比较的字符串
        System.out.println(s3.equalsIgnoreCase(s)); // 忽略大小写的比较字符串

        // 字符串的遍历
        String s6 = "hello world";
        for (int i = 0; i < s6.length(); i++) {
            System.out.println(s6.charAt(i));
        }
    }
}
