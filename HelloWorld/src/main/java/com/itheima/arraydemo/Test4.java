package com.itheima.arraydemo;

import java.util.ArrayList;
import java.util.Scanner;

public class Test4 {
    static void main() {
        /*
            键盘录入一些1~100之间的整数，并添加到集合中。
            直到集合中所有数据和超过200为止。
         */
        int sum = 0;
        ArrayList<Integer> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            int num = Integer.parseInt(str);
            if (num < 1 || num > 100) {
                System.out.println("输入的数字不在1~100之间");
                continue;
            }
            sum += num;
            System.out.println("当前和为：" + sum);
            list.add(num);
            if (sum > 200) {
                break;
            }
        }
        System.out.println(list);
    }
}
