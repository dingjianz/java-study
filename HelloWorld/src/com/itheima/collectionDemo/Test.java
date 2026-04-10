package com.itheima.collectionDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Test {
    public static void main(String[] args) {
      /*
        单列集合Collection 和双列集合 Map
            Collection 是单列集合的祖宗接口，它的功能是全部单列集合都可以继承使用的。

            1.单列集合Collection：
                 1.1 List: ArrayList LinkedList Vector
                 1.2 Set: HashSet TreeSet
                List系列集合：添加的元素是有序、可重复、有索引
                Set系列集合：添加的元素是无序、不重复、无索引


                public boolean add(E e) {} 把给定的对象添加到当前集合中
                public void clear() {} 清空集合中的元素
                public boolean remove(Object o) {} 删除集合中指定的元素
                public boolean contains(Object o) {} 判断当前集合中是否包含给定的元素
                public boolean isEmpty() {} 判断当前集合是否为空
                public int size() {} 获取当前集合的元素个数


       */

        /*
            注意点： Collection 是一个接口，我们不能直接创建它的对象
            集合的实现类有：ArrayList LinkedList Vector HashSet TreeSet
         */

        // 目的：为了学习Collection接口中的方法
        // 自己在做一些练习的时候，还是按照之前的方式去创建对象
        Collection<String> coll = new ArrayList<>();

        // 1.添加元素
        coll.add("hello");
        coll.add("world");
        System.out.println(coll);
    }
}
