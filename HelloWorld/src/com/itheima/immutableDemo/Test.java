package com.itheima.immutableDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
    /*
    不可变集合：
        1. 不可变集合创建好后，集合中的元素无法进行修改，长度也不可变
        2. 只能进行查询操作
     */

        /*
        创建不可变的List集合
         */

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"hello","world","java");

        List<String> list1 = Collections.unmodifiableList(list);
         // list1.add("hello"); 报错
        List<String> list2 = List.of("hello","world","java");
        // list2.add("hello"); 报错
        List<String> list3 = List.copyOf(list);
        // list3.add("hello"); 报错

        System.out.println(list3);

    }
}
