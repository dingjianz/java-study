package com.itheima.collectionDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class LambdaDemo {
    public static void main(String[] args) {
        /*
            lambda 表达式遍历
            default void forEach(Consumer<? super T> action)
         */

        // 1.创建集合
        Collection<String> coll = new ArrayList<>();

        coll.add("hello");
        coll.add("world");
        coll.add("java");

        coll.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        System.out.println("-----------------");

        coll.forEach(s -> System.out.println(s));
    }
}
