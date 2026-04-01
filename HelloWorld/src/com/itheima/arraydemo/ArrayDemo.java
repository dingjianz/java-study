package com.itheima.arraydemo;

import java.util.Arrays;
import java.util.Comparator;

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
        // 如果查找的元素不存在，就返回-（插入点）-1
        System.out.println(Arrays.binarySearch(arr, 5)); // 4
        System.out.println(Arrays.binarySearch(arr, 20)); // -11

        /*
            copyOf 拷贝数组
            如果新数组的长度小于老数组的长度，会部分拷贝
            如果新数组的长度大于老数组的长度，会进行填充，默认0
         */
        int[] arr2 = Arrays.copyOf(arr, 5); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(arr2));

          /*
            copyOfRange 拷贝数组
            细节：包头不包尾 包左不包右
            如果新数组的长度大于老数组的长度，会进行填充，默认0
         */
        int[] arr3 = Arrays.copyOfRange(arr, 0, 9);
        System.out.println(Arrays.toString(arr3)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // fill 填充数组
        Arrays.fill(arr, 10);
        System.out.println(Arrays.toString(arr)); // [10, 10, 10, 10, 10, 10, 10, 10, 10, 10]

        // sort 排序 默认升序
        int[] arr4 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        Arrays.sort(arr4);
        System.out.println(Arrays.toString(arr4)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        /*
        底层原理：
        利用插入排序 + 二分查找的方式进行排序的
        默认把0索引的数据当做是有序的序列，1索引到最后认为是无序的序列
        遍历无序的序列得到里面的每一个元素，假设当前遍历得到的元素是A元素
        

         */
        int[] arr5 = {10, 11, 9, 5, 7, 6, 8, 4, 3, 2, 1};
        Arrays.sort(arr5, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

    }
}
