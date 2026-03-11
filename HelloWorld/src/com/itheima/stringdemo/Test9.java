package com.itheima.stringdemo;

import java.util.Random;
import java.util.Scanner;

public class Test9 {
    public static void main(String[] args) {
        /*
           随机打乱字符的顺序
           eg: abcd -> dbca
         */

        // 定义一个字符串
        String s = "abcd";

        // 获取字符串的 char[]
        char[] charArray = s.toCharArray();

        // 创建 Random 对象
        Random r = new Random();

        for (int i = 0; i < charArray.length; i++) {
            int index = r.nextInt(charArray.length);
            char temp = charArray[i];
            charArray[i] = charArray[index];
            charArray[index] = temp;
        }

        // 创建 String 对象 将打乱之后的char[] 赋给它
        String result = new String(charArray);
        System.out.println(result);
    }
}
