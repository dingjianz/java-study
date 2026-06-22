package com.itheima.collectionDemo;

import java.util.ArrayList;
import java.util.Collection;

public class ForDemo {
    public static void main(String[] args) {
        /*
            增加for循环
              增强for的底层就是迭代器，为了简化迭代器的代码书写的
              它是JDK5之后出现的，其内部原理就是一个 Iterator迭代器
              所有的单列集合和数组才能用增强for进行遍历
              for (集合元素的类型 临时变量 : 集合对象) {}
              for(String s : list) {
                system.out.println(s);
              }
         */

        // 1.创建集合对象
        Collection<String> coll = new ArrayList<>();
        coll.add("hello");
        coll.add("world");
        coll.add("java");

        // 2.使用增强for进行遍历
        // s 其实就是一个第三方遍历，在循环过程中依次表示集合中的每一个元素
        for (String s : coll) {
            System.out.println(s);
        }
    }
}
