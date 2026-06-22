package com.itheima.stringdemo;

import java.util.Scanner;

public class Test7 {
    static void main() {
        /*
           键盘录入字符串，将该字符串进行反转，
           当输入拜拜的时候程序停止运行
           例如，键盘录入abc，输出结果cba
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串:");
        String str = scanner.next();
        while (!str.equals("拜拜")) {
            StringBuilder sb = new StringBuilder(str);
            String newStr = sb.reverse().toString();
            System.out.println(newStr);
            str = scanner.next();
        }
    }
}
