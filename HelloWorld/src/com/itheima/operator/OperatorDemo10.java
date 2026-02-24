package com.itheima.operator;

public class OperatorDemo10 {
    public static void main(String[] args) {
        // 数组去重
        int[] arr = {1, 1, 2, 3, 4, 5, 5, 6, 6};
        int flag = 0;
        for (int flag2 = 1; flag2 < arr.length; flag2++) {
            if (arr[flag] != arr[flag2]) {
                flag++;
                arr[flag] = arr[flag2];
            }
        }
        for (int i = 0; i < flag; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
