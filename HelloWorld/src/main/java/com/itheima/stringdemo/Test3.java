package com.itheima.stringdemo;

public class Test3 {
    static void main() {
        int[] arr = new int[]{1, 2, 3};
        String sb = "[";
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (i != arr.length - 1) {
                sb = sb + num + ", ";
            } else {
                sb = sb + num;
            }
        }
        sb += "]";
        System.out.println(sb);
    }
}
