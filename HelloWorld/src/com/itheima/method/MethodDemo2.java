package com.itheima.method;

public class MethodDemo2 {
    public static void main(String[] args) {
        // 需求：定义一个方法，获取一个整数数组的字符串形式，并把结果打印在控制台。
        int[] arr = {1, 2, 3, 4, 5};
        String result = getArrayString(arr);
        System.out.println(result);
    }

    public static String getArrayString(int[] arr) {
        String result = "[";
        for (int i = 0; i < arr.length; i++) {
            // IDE 波浪线提示 “String concatenation '+=' in loop” 是性能警告：在循环里用 + 或 += 每次都会创建新的 String 对象。推荐改用 StringBuilder（或直接用 Arrays.toString(arr)）以避免频繁分配和复制
            result += arr[i];
            if (i != arr.length - 1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
}
