package com.itheima.toolclasstest;

public class ArrayUtils {
    // 私有化构造方法，禁止创建对象
    private ArrayUtils() {}

    // 定义一个静态方法，接收一个int类型的数组，返回该数组的最大值
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

}
