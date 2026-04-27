package com.itheima.streamDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Test3 {
    /*
    Stream流中的中间方法
        1. Stream<T> filter(Predicate<? super T> predicate) 过滤
        2. Stream<T> limit(long maxSize) 获取前几个元素
        3. Stream<T> skip(long n) 跳过前几个元素
        4. Stream<T> distinct() 元素去重，通过hashCode() 和 equals() 去除重复元素
        5. <R> Stream<R> map(Function<? super T, ? extends R> mapper) 转换流中的数据类型
        6. static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b) 合并a和b两个流为一个流

        注意1：中间方法，返回新的Stream流，原来的Stream流只能使用一次，建议使用链式编程
        注意2：修改Stream流中的数据，不会影响原来集合或者数组中的数据
     */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张三", "张三丰", "张翠山", "张无忌", "趙敏", "趙敏");

        list.stream().filter(name -> name.startsWith("张")).forEach(System.out::println);

        System.out.println("--------------------------");

        list.stream().limit(2).forEach(System.out::println);

        System.out.println("--------------------------");

        list.stream().skip(2).forEach(System.out::println);

        System.out.println("--------------------------");

        list.stream().distinct().forEach(System.out::println);

        System.out.println("--------------------------");

        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "aaa", "bbb", "ccc");

        Stream.concat(list.stream(), list2.stream()).forEach(System.out::println);

        System.out.println("--------------------------");
        // 需求：只获取字符串中的数字并打印
        ArrayList<String> list3 = new ArrayList<>();
        Collections.addAll(list3, "a-1", "b-2", "c-3");
        list3.stream().map(s -> Integer.parseInt(s.split("-")[1])).forEach(System.out::println);

    }

}
