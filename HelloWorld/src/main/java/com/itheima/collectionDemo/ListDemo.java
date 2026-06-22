package com.itheima.collectionDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListDemo {
    public static void main(String[] args) {
        /*
          List集合的特点
            1.有序：存储和取出的元素一致
            2.有索引：可以通过索引操作元素
            3.可重复：存储的元素可以重复

            List集合特有的方法
                Collection 的方法List 都继承了
                List 集合因为有索引 所以多了很多索引操作的方法
                    1.void add(int index,E element)：在指定位置添加元素
                    2.E get(int index)：获取指定索引处的元素
                    3.E set(int index,E element)：修改指定索引处的元素 返回被修改的元素
                    4.E remove(int index)：删除指定索引处的元素

             List能用Collection集合的所有方法
               List系列集合的五种遍历方式
                1. 迭代器：在遍历的过程中需要删除元素，请使用迭代器
                2. 列表迭代器：在遍历的过程中需要添加元素，请使用列表迭代器
                3. 增强for
                4. Lambda表达式
                5. 普通for循环

         */

        // 1.创建集合
        List<String> list = new ArrayList<>();

        // 2.添加元素
        list.add("hello");
        list.add("world");
        list.add("java");

        // 3.在指定的位置添加元素
        // 细节：原来索引上的元素会依次往后移
        list.add(1, "qqq");

        list.remove("qqq"); // 删除指定元素
        list.remove(1); // 删除指定索引的元素

        // E set(int index,E element)：修改指定索引处的元素 返回被修改的元素 index不能超出集合的长度
        String s = list.set(1, "jianding9");
        System.out.println(s); // java
        System.out.println(list);

        System.out.println("-------------------");

        // 迭代器
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s1 = it.next();
            System.out.println(s1);
        }

        System.out.println("-------------------");

        // 列表迭代器
        // 在迭代器的基础上新增了 add的方法 E add(E element)
        ListIterator<String> lit = list.listIterator();
        while (lit.hasNext()) {
            String s2 = lit.next();
            System.out.println(s2);
//            if(s2.equals("hello")) {
            if(s2 == "hello") {
                lit.add("javaee");
            }
        }

        System.out.println("-------------------");

        // 增强for循环
        for (String s1 : list) {
            System.out.println(s1);
        }

        System.out.println("-------------------");

        // Lambda表达式
        list.forEach(s3 -> System.out.println(s3));

        System.out.println("-------------------");

        // 普通for循环
        for (int i = 0; i < list.size(); i++) {
            String s4 = list.get(i);
            System.out.println(s4);
        }
    }
}
