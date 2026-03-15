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


        // 二维数组
        int[][] arr8 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int i = 0; i < arr8.length; i++) {
            for (int j = 0; j < arr8[i].length; j++) {
                System.out.print(arr8[i][j] + " ");
            }
            System.out.println();
        }

        // 二维数组的动态初始化
        /*
            格式：数据类型[][] 数组名 = new 数据类型[行数][列数];
            行数表示数组中元素的个数，列数表示数组中元素的个数。
         */
    }
}
