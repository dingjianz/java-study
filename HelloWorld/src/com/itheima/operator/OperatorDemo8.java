package com.itheima.operator;

import java.util.Random;

public class OperatorDemo8 {
    public static void main(String[] args) {
        // 数组的静态初始化
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = {4, 5, 6};

        double[] arr3 = new double[]{1.1, 2.2, 3.3};
        double[] arr4 = {4.4, 5.5, 6.6};

        String[] arr5 = new String[]{"hello", "world"};
        String[] arr6 = {"java", "python"};

        // 打乱数组中的数据
        int[] arr7 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Random r = new Random();
        for (int i = 0; i < arr7.length; i++) {
            int temp = arr7[i];
            int randomIndex = r.nextInt(arr7.length);
            arr7[i] = arr7[randomIndex];
            arr7[randomIndex] = temp;
        }
        for (int i = 0; i < arr7.length; i++) {
            System.out.println(arr7[i]);
        }
    }
}
