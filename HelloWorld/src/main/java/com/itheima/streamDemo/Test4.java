package com.itheima.streamDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class Test4 {
    /*
    Stream流中的终结方法
        1. void forEach(Consumer<? super T> action) 遍历
        2. long count() 统计流中的元素个数
        3. toArray() 返回一个数组
        4. collect(Collector<? super T, ?, R> collector) 收集
     */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "张三丰", "张翠山", "张无忌", "趙敏", "周芷若");

        System.out.println(list.stream().count()); // 6

        Object[] array = list.stream().toArray();
        System.out.println(Arrays.toString(array));

        list.stream().toArray(value -> new String[value]);

    }

}
