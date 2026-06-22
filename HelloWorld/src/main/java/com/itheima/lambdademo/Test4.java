package com.itheima.lambdademo;

import java.util.Arrays;

public class Test4 {
    /*
    Lambda表达式简化Comparator接口的匿名形式
    定义数组并存储一些字符串，利用Arrays中的sort方法进行排序:
    按照字符串的长度进行排序，短的在前面，长的在后面。(暂时不比较字符串里面的内容)
     */
    public static void main(String[] args) {
        String[] arr = {"aaa", "bb", "cccc", "dddddd"};

        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());
        System.out.println(Arrays.toString(arr)); // [bb, aaa, cccc, dddddd]
    }
}
