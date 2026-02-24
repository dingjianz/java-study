package com.itheima.method;

public class MethodDemo6 {
    public static void main(String[] args) {
        /*
           需求：排序
        * */
        int[] arr1 = {2, 1, 5, 6, 11};
        int[] arr2 = {3, 8, 9};
        // 排序
        sort(arr1);
        sort(arr2);
        printArr(arr1);
        printArr(arr2);

    }

    public static void printArr(int[] arr) {
        String result = "arr = [";
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                result += arr[i] + "]";
            } else {
                result += arr[i] + ", ";
            }
        }
        System.out.println(result);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
