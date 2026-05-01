package com.itheima.streamDemo;

import java.util.*;
import java.util.stream.Collectors;

public class Test5 {
    /*
    Stream流中的 collect(Collector<? super T, ?, R> collector) 收集
     */

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张三-男-10", "李四-男-21", "李敏-女-18");

        // 需求：将list中，所有男性的姓名，存储到一个新的List集合中
        List<String> list2 = list.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toList());

        System.out.println(list2);

        // Set 可以去重
        // 需求：将list中，所有男的姓名，存储到一个Set集合中
        Set<String> list3 = list.stream()
                .filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toSet());

        System.out.println(list3);

        // 需求收集到 Map集合中
        // key：姓名 value：年龄
        Map<String, Integer> map = list.stream()
                .collect(Collectors.toMap(s -> s.split("-")[0], s -> Integer.parseInt(s.split("-")[2])));
        System.out.println(map);
    }

}