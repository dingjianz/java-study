package com.itheima.arraydemo;

import java.util.Arrays;

public class ArrayDemo {
    static void main() {
        /*
            public static String toString(int i)  把int转换成字符串
            public static int binarySearch(int[] a, int key)  二分查找
            public static int[] copyOf(int[] original, int newLength)  拷贝数组
            public static int[] copyOfRange(int[] original, int from, int to)  拷贝数组（指定范围）
            public static void fill(int[] a, int val)  填充数组
            public static void sort(int[] a)  按照默认的规则进行数组排序
            public static void sort(int[] a, 排序规则) 按照指定规则排序
         */
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

         // binarySearch 二分法前提是：数组中的元素必须是有序的
        System.out.println(Arrays.binarySearch(arr, 5)); // 4
        System.out.println(Arrays.binarySearch(arr, 20)); // -11

    }
}
