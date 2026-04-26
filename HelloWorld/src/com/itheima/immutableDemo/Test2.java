package com.itheima.immutableDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Test2 {
    public static void main(String[] args) {
        /*
        创建不可变的Set集合

        细节：当我们要获取一个不可变的Set集合时，里面的参数一定要保证唯一性
         */
        Set<Integer> set =  Set.of(1,2,3);
        // set.add(4);  // 添加元素会报错

        Set<Integer> set2 = Set.copyOf(set);
        // set2.add(4);  // 添加元素会报错

        Set<Integer> set3 = Collections.unmodifiableSet(set);
        // set3.add(4);  // 添加元素会报错
        System.out.println(set3);

    }
}
