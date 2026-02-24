package com.itheima.method;

public class MethodDemo5 {
    public static void main(String[] args) {
        /*
           需求：排序 + 取中位数
        * */

        int[] arr1 = {1, 2, 5, 6, 11};
        int[] arr2 = {3, 8, 9};
        int[] arr3 = new int[arr1.length + arr2.length];
        int index1 = 0;
        int index2 = 0;

        for (int i = 0; i < arr3.length; i++) {
            if (index1 == arr1.length) {
                arr3[i] = arr2[index2];
                index2++;
                continue;
            }

            if (index2 == arr2.length) {
                arr3[i] = arr1[index1];
                index1++;
                continue;
            }

            if (arr1[index1] < arr2[index2]) {
                arr3[i] = arr1[index1];
                index1++;
            } else {
                arr3[i] = arr2[index2];
                index2++;
            }
        }

        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }

        // 取中位数
        if (arr3.length % 2 == 0) {
            int mid1 = arr3[arr3.length / 2 - 1];
            int mid2 = arr3[arr3.length / 2];
            System.out.println("\n中位数是：" + (mid1 + mid2) / 2.0);
        } else {
            int mid = arr3[arr3.length / 2];
            System.out.println("\n中位数是：" + mid);
        }
    }
}
