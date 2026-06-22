package com.itheima.collectionDemo;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo3 {
    public static void main(String[] args) {
        /*
           TreeSet对象排序练习题
            需求:请自行选择比较器排序和自然排序两种方式;
            要求:存入四个字符串，"c"，"ab"，"df"，"qwer"
            按照长度排序，如果一样长则按照首字母排序
         */

        // 第二种比较方式：自定义比较器Comparator
        TreeSet<String> ts = new TreeSet<>((o1, o2) -> {
            int i = o1.length() - o2.length();
            return i != 0 ? i : o1.compareTo(o2);
        });

        ts.add("c");
        ts.add("ab");
        ts.add("df");
        ts.add("qwer");
        System.out.println(ts);

    }
}
