package com.itheima.operator;

import java.util.Random;

public class OperatorDemo9 {
    public static void main(String[] args) {
        Random r = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length;) {
            int num = r.nextInt(100) + 1; // 生成1
            int count = 0;
            for (int j = 0; j < arr.length; j++) {
                if (num == arr[j]) {
                    count++;
                    break;
                }
            }
            if (count == 0) {
                arr[i] = num;
                i++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
