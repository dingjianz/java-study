package com.itheima.collectionDemo;

import java.util.ArrayList;
import java.util.List;

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

         */

        // 1.创建集合
        List<String> list = new ArrayList<>();

        // 2.添加元素
        list.add("hello");
        list.add("world");
        list.add("java");

        // 3.在指定的位置添加元素
        // 细节：原来索引上的元素会依次往后移
        list.add(1,"qqq");

        list.remove("qqq"); // 删除指定元素
        list.remove(1); // 删除指定索引的元素

        // E set(int index,E element)：修改指定索引处的元素 返回被修改的元素 index不能超出集合的长度
        String s = list.set(1,"jianding9");
        System.out.println(s); // java

        System.out.println(list);

    }
}
