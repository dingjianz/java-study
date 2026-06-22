package com.itheima.collectionDemo;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        /*
            LinkedList集合的特点
              底层数据是双向链表，查询慢，增删快，但首尾的操作速度是极快的
              所以多了很多首尾操作的方法
              1.public void addFirst(E e)：在集合的开头添加元素
              2.public void addLast(E e)：在集合的末尾添加元素
              3.public E getFirst()：获取集合的第一个元素
              4.public E getLast()：获取集合的末尾元素
              5.public E removeFirst()：删除集合的第一个元素
              6.public E removeLast()：删除集合的末尾元素
              7.public E poll()：获取并删除集合的开头元素
              8.public E pollLast()：获取并删除集合的末尾元素
              9.public off 添加元素
         */
        LinkedList<String> list = new LinkedList<>(Arrays.asList("hello", "world", "java"));
        System.out.println(list);
        String poll = list.poll();
        System.out.println(poll);
        System.out.println(list.offer("javaee"));

    }
}