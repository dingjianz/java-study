package com.itheima.collectionDemo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        /*
            利用Set系列的集合，添加字符串，并使用多种方式遍历
            迭代器
            增强for
            Lambda表达式

        Set集合的实现类的特点：
            HashSet: 无序、不重复、无索引
            LinkedHashSet: 有序、不重复、无索引
            TreeSet: 可排序、不重复、无索引
         */

        // 1.创建集合
        Set<String> set = new HashSet<>();

        // 2.添加元素
        // 如果当前元素是第一次添加，那么可以成功添加，返回 true
        set.add("张三");
        Boolean r = set.add("张三");
        System.out.println(r); // false
        set.add("李四");
        set.add("王五");

        // 3.打印集合 无序 存和取的规则不一致
        System.out.println(set);

        // 4.遍历集合
        // 4.1迭代器
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s);
        }

        System.out.println("-------------------");

        // 4.2 增强for
        for (String s : set) {
            System.out.println(s);
        }

        System.out.println("-------------------");

        // 4.3 Lambda表达式
        set.forEach(s -> System.out.println(s));
        set.forEach(System.out::println);
    }
}
