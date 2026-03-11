package com.itheima.stringdemo;

import java.util.Scanner;

public class Test8 {
    public static void main(String[] args) {
        /*
            键盘录入任意字符串，请按长度为 8 拆分
            每个输入字符串并进行输出长度不是 8 整数倍的字符串
            请在后面补数字 0，空字符串不处理。
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串:");
        String str = scanner.next();
            
        // 空字符串不处理
        if (str == null || str.length() == 0) {
            return;
        }
            
        int length = str.length();
        for (int i = 0; i < length; i += 8) {
            StringBuilder sb = new StringBuilder();
            String segment;
            if (i + 8 > length) {
                // 最后一段不足 8 个字符
                segment = str.substring(i);
                sb.append(segment);
                // 补充 0 到长度为 8
                int zerosNeeded = 8 - segment.length();
                for (int j = 0; j < zerosNeeded; j++) {
                    sb.append('0');
                }
            } else {
                // 正好 8 个字符
                segment = str.substring(i, i + 8);
                sb.append(segment);
            }
            System.out.println(sb.toString());
        }
    }
}
