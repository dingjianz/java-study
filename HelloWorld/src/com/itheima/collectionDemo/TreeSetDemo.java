package com.itheima.collectionDemo;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        /*
            TreeSet 的特点：不重复、无索引、可排序
            可排序：按照元素的默认规则（从大到小）排序
            TreeSet 集合底层是基于红黑树的数据结构实现排序的，增删改查性能都较好

            TreeSet集合默认的规则：
            对于数值类型：Integer、int、long、short、byte 按照升序排序
            对于字符、字符串类型：按照字符在ASCII码表中的数字顺序排序

            eg："aaa" > "ab" > "aba" > "cd" > "qwer"
         */


        //创建TreeSet集合对象
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(1);
        System.out.println(ts.add(1)); // false
        ts.add(3);
        ts.add(2);
        System.out.println(ts);

        for (Integer t : ts) {
            System.out.println(t);
        }

        System.out.println("-------------");

        Iterator<Integer> it = ts.iterator();
        while (it.hasNext()) {
            Integer t = it.next();
            System.out.println(t);
        }
        System.out.println("-------------");

        ts.forEach(System.out::println);
    }
}
