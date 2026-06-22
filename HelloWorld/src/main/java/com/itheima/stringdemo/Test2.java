package com.itheima.stringdemo;

import java.util.Scanner;

public class Test2 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符：");
        String str = sc.nextLine();
        int lowerCount = 0; // 小写字符个数
        int upperCount = 0; // 大写字符个数
        int numberCount = 0; // 数字字符个数

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                lowerCount++;
            } else if (ch >= 'A' && ch <= 'Z') {
                upperCount++;
            } else if (ch >= '0' && ch <= '9') {
                numberCount++;
            } else {
                System.out.println("当前字符不参数统计");
            }
        }

        System.out.println("小写字符个数：" + lowerCount);
        System.out.println("大写字符个数：" + upperCount);
        System.out.println("数字字符个数：" + numberCount);
    }
}
