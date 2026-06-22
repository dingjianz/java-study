package com.itheima.streamDemo;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        /*
            创建结合添加元素，完成以下需求：
            1.把所有以“张”开头的元素存储到新集合中；
            2.把“张”开头的元素长度为3 的元素再存储到新集合中
            3.遍历打印最终结果
         */

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张三丰", "张强");

        // 1.把所有以“张”开头的元素存储到新集合中；
        ArrayList<String> list2 = new ArrayList<>();

        for (String s : list) {
            if (s.startsWith("张")) {
                list2.add(s);
            }
        }

        System.out.println(list2);

        // 2.把“张”开头的元素长度为3 的元素再存储到新集合中
        ArrayList<String> list3 = new ArrayList<>();
        for (String s : list2) {
            if (s.length() == 3) {
                list3.add(s);
            }
        }

        for (String s : list3) {
            System.out.println(s);
        }


        System.out.println("---------------");

        // 简写
        list.stream().filter(name -> name.startsWith("张")).filter(name -> name.length() == 3).forEach(System.out::println);
    }
}
