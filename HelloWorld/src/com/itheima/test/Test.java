package com.itheima.test;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*
            彩票中奖案例，生成一个7位的随机数表示彩票号码，用户输入一个7位的数字，如果与随机数相同则中奖，否则提示未中奖。
         */

        // 1. 生成一个7位的随机数表示彩票号码
        int lotteryNumber = (int) (Math.random() * 9000000) + 1000000; // 生成一个7位的随机数

        // 2. 用户输入一个7位的数字
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入一个7位的数字作为彩票号码：");
        int userInput = sc.nextInt();

        // 3. 判断用户输入的数字是否与随机数相同，如果相同则中奖，否则提示未中奖
        if (userInput == lotteryNumber) {
            System.out.println("恭喜你中奖了！彩票号码是：" + lotteryNumber);
        } else {
            System.out.println("很遗憾，未中奖。彩票号码是：" + lotteryNumber);
        }

        sc.close();

    }
}
