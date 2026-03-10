package com.itheima.stringdemo;

import java.util.Locale;

public class Test4 {
    static void main() {
        /*
          substring(int beginIndex, int endIndex) 包左不包右 包头不包尾
          substring(int beginIndex) 直到末尾

         */

        String str = "abcdefg";
        String res1 = str.substring(1, 5);
        String res2 = str.substring(1);

        System.out.println(str); // abcdefg
        System.out.println(res1); // bcde
        System.out.println(res2); // bcdefg

        String str2 = "HELLO";
        String res3 = str2.substring(0, 1) + "***";
        String res4 = str2.charAt(0) + "***";
        System.out.println(res3); // "H***"
        System.out.println(res4); // "H***"

        String str3 = "你玩的好菜啊，TMD，不知道你怎么玩TMD";
        String res5 = str3.replace("TMD", "***");
        String res6 = str3.replaceFirst("TMD", "***");
        System.out.println(res5);
        System.out.println(res6);


        System.out.println("========== 正则表达式案例 ==========");

        // 案例1: 替换所有数字
        String str4 = "我的手机号是13812345678，QQ号是123456789";
        String res7 = str4.replaceAll("\\d", "*");  // \d 匹配任意数字
        System.out.println("替换所有数字: " + res7);

        // 案例2: 替换连续的数字
        String res8 = str4.replaceAll("\\d+", "***");  // \d+ 匹配一个或多个数字
        System.out.println("替换连续数字: " + res8);

        // 案例3: 手机号脱敏（保留前3位和后4位）
        String phone = "13812345678";
        String res9 = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        System.out.println("手机号脱敏: " + res9);

        // 案例4: 替换所有空格
        String str5 = "hello   world  java   programming";
        String res10 = str5.replaceAll("\\s+", " ");  // \s+ 匹配一个或多个空白字符
        System.out.println("合并空格: " + res10);

        // 案例5: 只替换第一个数字序列
        String str6 = "订单号: 20240310001, 金额: 998元";
        String res11 = str6.replaceFirst("\\d+", "***");
        System.out.println("只替换第一个数字: " + res11);

        // 案例6: 替换所有英文字母
        String str7 = "Hello123World456";
        String res12 = str7.replaceAll("[a-zA-Z]+", "*");
        System.out.println("替换所有字母: " + res12);

        // 案例7: 邮箱脱敏
        String email = "zhangsan@qq.com";
        String res13 = email.replaceAll("(\\w)\\w+(@\\w+)", "$1***$2");
        System.out.println("邮箱脱敏: " + res13);

        // 是否包含
        String str8 = "ABCDE";
        System.out.println(str8.contains("abc")); // false
        System.out.println(str8.contains("A")); // true
        System.out.println(str8.startsWith("A")); // true
        System.out.println(str8.startsWith("A", 1)); // false 第二个参数是规定起始位置
        System.out.println(str8.endsWith("E")); // true

        int i1 = str8.indexOf(65); // 0
        int i2 = str8.indexOf(129); // -1
        int i3 = str8.indexOf("A"); // 0
        int i4 = str8.indexOf("B");
        int i5 = str8.indexOf("B", 1);

        String str9 = "";
        System.out.println(str8.isEmpty()); // false
        System.out.println(str8.isBlank()); // false
        System.out.println(str9.isEmpty()); // true
        System.out.println(str9.isBlank()); // true

        // isEmpty 和 isBlank 的区别
        String str10 = "   ";  // 只有空格
        System.out.println("空格字符串 isEmpty: " + str10.isEmpty()); // false
        System.out.println("空格字符串 isBlank: " + str10.isBlank()); // true ⭐关键区别

        String str11 = "ABCDE";
        String str11LowerCase = str11.toLowerCase();
        System.out.println(str11LowerCase); // abcde
        System.out.println(str11LowerCase.toUpperCase()); // ABCDE
        char[] charArray = str11LowerCase.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }

        String str12 = " A B C ";
        System.out.println(str12.trim()); // ABC


    }
}
