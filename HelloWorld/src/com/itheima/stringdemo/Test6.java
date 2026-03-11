package com.itheima.stringdemo;

public class Test6 {
    static void main() {
        /*
            说明下一下代码需求
            需求：将数组中的元素拼接成 "[1, 2, 3]"
         */
        int[] arr = {1, 2, 3};
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                sb.append(arr[i]).append(", ");
            } else {
                sb.append(arr[i]).append("]");
            }
        }
        System.out.println(sb);
    }
}
