package com.itheima.arraydemo;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
       /*
            集合：是一种长度可变的容器
            boolean add(E e)
            void add(int index,E e)
            E get(int index)
            E set(int index,E e)
            E remove(int index)
            void remove(Object e)
            void clear()
            int size()
        */

        // ArrayList<String> list = new ArrayList<String>();
        // jdk7 新特性 泛型可以不重复写 但是尖括号要保留
        ArrayList<String> list = new ArrayList<>();

        /*
            ArrayList add返回的值永远都是 true
            但是在Java中，有很多集合，例如 HashSet、HashMap 等，
            它们都有 add 方法，返回值可能不是 true。因为他们的元素要唯一


            在集合中无法直接添加基本数据类型(eg: byte short int long float double char boolean)
            只能添加引用类型(对象），因为基本数据类型无法作为键值
            如果要在集合里面添加基本数据类型，则必须将其转成对应的包装类
         */
        boolean res = list.add("hello");
        System.out.println(res); // true

        list.add("world");

        list.addFirst("jian");
        /*
            如果集合的长度为3，那么add的索引范围是[0,3]
            0 - 2 是已经存在的索引
            3 就是把当前元素添加到集合的末尾，等同于一个参数的add方法
            如果要添加的索引超出了这个范围，程序就会报错
         */
        list.add(2, "ding");
        // list.add(1); // Required type:String Provided: int
        System.out.println(list);

        String s1 = list.getFirst();
        String s2 = list.get(1);
        System.out.println(s1);
        System.out.println(s2);

        boolean res3 = list.remove("jian");
        System.out.println(res3); // true

        String s3 = list.remove(1);
        System.out.println(s3);
        // list.clear();
        System.out.println(list);

        int size = list.size();
        System.out.println(size);

        String s4 = list.set(0, "first"); // s4 = hello  返回被替换的那个值
        System.out.println(s4);
        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
