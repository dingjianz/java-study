package com.itheima.arraydemo;

public class Test6 {
    static void main() {
        // 定义一个方法自己实现toBinaryString方法的效果，将一个十进制整数转成字符串表示的二进制
        int number = 16548;
        System.out.println(toBinaryString(number));
        System.out.println(Integer.toBinaryString(number));

    }

    // 静态方法 n:十进制数
    public static String toBinaryString(int n) {
        String result = "";
        while (n != 0) {
            result = (n % 2) + result;
            n /= 2;
        }
        return result;
    }
}
