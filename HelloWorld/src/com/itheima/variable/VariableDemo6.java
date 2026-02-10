package com.itheima.variable;
import java.util.Scanner;

public class VariableDemo6 {
    /*
    让 Scanner 类来帮助我们实现键盘录入功能。
    需求：提示用户分别输入两个整数，并将两个整数相加后的结果在控制台输出。
     */
    public static void main() {
        // 创建 Scanner 对象
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入第一个整数
        System.out.println("请输入第一个整数：");
        int num1 = scanner.nextInt();

        // 提示用户输入第二个整数
        System.out.println("请输入第二个整数：");
        int num2 = scanner.nextInt();

        // 输出两个整数相加的结果
        int result = num1 + num2;
        System.out.println("两数之和是: " + result);

    }
}
