package com.itheima.arraydemo;

public class Test5 {
    static void main() {
        /*
            自己实现 parseInt 方法的效果，将字符串形式的数据转成整数。
            要求：
            字符串中只能是数字不能有其他字符
            最少一位最多10位
            0不能开头
         */

        // 1.定义一个字符串
        String str = "12343567890";

        if (!str.matches("[1-9]\\d{0,9}")) {
            System.out.println("字符串格式不正确");
        } else {
            int num = 0;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                num = num * 10 + (ch - '0');
            }
            System.out.println(num);
        }
    }
}
