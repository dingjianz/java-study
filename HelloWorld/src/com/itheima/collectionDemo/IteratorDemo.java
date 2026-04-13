package com.itheima.collectionDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorDemo {
    public static void main(String[] args) {
        /*
            迭代器：集合的遍历方式
            集合的遍历方式：
                1.迭代器
                    迭代器在Java中的类是Iterator，迭代器是集合专用的遍历方式。
                    迭代器在Java中的接口是Iterable，Iterable接口是一个抽象接口，Iterable接口中有一个抽象方法iterator()。
                    Iterator接口中有一个抽象方法next()，用于获取集合中的下一个元素，并移动指针。
                    Iterator接口中还有一个抽象方法hasNext()，用于判断集合中是否有下一个元素。
                    Iterator<E> iterator() 返回迭代器对象，默认指向当前集合的0索引

                    迭代器的注意细节：
                     1. 报错：java.util.NoSuchElementException: 迭代器已经没有元素了
                     2. 迭代器遍历完毕，指针不会复位
                     3.遍历循环中，只能用一次next方法
                     4. 迭代器遍历集合的过程中，不能通过集合去添加或者删除元素
                        如果实在要删除，迭代器遍历集合的时候，必须使用迭代器的remove方法 it.remove();
                        如果要添加，暂时还是实现不了。

                2.foreach
                3.lambda
                4.stream
                5.JDK8新增的集合遍历方式
         */

        // 1.创建集合
        Collection<String> coll = new ArrayList<>();
        coll.add("hello");
        coll.add("world");
        coll.add("java");

        // 2.获取迭代器
        // 迭代器好比是一个箭头。默认指向集合0的索引处
        Iterator<String> it = coll.iterator();

        // 3.利用循环不断的去获取集合中的每一个元素
        while (it.hasNext()) {
            // next方法的两件事情：获取元素并移动指针
            String s = it.next();
            System.out.println(s);
        }

        // 当上面的循环结束之后，迭代器的指针已经指向了最后没有元素的索引处
        // System.out.println(it.next()); // NoSuchElementException

    }
}
