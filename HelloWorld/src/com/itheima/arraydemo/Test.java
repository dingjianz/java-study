package com.itheima.arraydemo;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
       /*
            集合：是一种长度可变的容器
        */

        ArrayList<String> list = new ArrayList<String>();

        list.add("hello");
        list.add("world");
        // list.add(1); // Required type:String Provided: int
        System.out.println(list);

        String s1 = list.getFirst();
        String s2 = list.get(1);
        System.out.println(s1);
        System.out.println(s2);
    }
}
