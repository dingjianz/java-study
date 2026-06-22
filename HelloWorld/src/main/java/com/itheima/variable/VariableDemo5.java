package com.itheima.variable;
import java.util.Scanner;

public class VariableDemo5 {
    /*
    让 Scanner 类来帮助我们实现键盘录入功能。
     */
    public static void main() {
        // 创建 Scanner 对象
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            // 提示用户输入一个整数
            int num = scanner.nextInt();
            System.out.println("输入的是整数" + num);
        } else if (scanner.hasNextDouble()) {
          // 提示用户输入一个小数
            double num = scanner.nextDouble();
            System.out.println("输入的是浮点数" + num);
        } else if (scanner.hasNext()) {
        // 提示用户输入一个文本（字符串）
            String str = scanner.next();
            System.out.println("输入的是字符串" + str);
        }
    }
}
