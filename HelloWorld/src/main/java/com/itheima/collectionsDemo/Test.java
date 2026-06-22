package com.itheima.collectionsDemo;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
       /*
       Collections类：集合工具类，操作集合的工具类
          public static <T> boolean addAll(Collection<T> c, T... elements)
              向集合中添加多个元素

           public static void shuffle(List<?> list) 使用默认随机源对列表进行随机排列

           public static <T> void sort(List<T> list) 排序
           public static <T> void sort(List<T> list, Comparator<? super T> c) 使用指定的规则排序

           public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
           public static <T> void swap(List<?> list, i, j) ：将指定列表中的元素位置交换
        */

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "李四", "王五", "赵六");
        System.out.println(list); // [张三, 李四, 王五, 赵六]

        Collections.shuffle(list);

        System.out.println( list); // [李四, 王五, 张三, 赵六]

    }
}
