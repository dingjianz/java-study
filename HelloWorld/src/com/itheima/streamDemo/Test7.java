package com.itheima.streamDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Test7 {
    /*
        创建一个ArrayList集合，添加以下字符，字符串中前面是姓名 后面是年龄
        "张三,23"
        "李四,24"
        "王五,25"
        保留年龄大于等于24岁的人 并将结果收集到Map集合当中
        姓名为键 年龄为值
     */

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张三,23", "李四,24", "王五,25");
        Map<String, Integer> map = list.stream()
                .filter(s -> Integer.parseInt(s.split(",")[1]) >= 24)
                .collect(Collectors.toMap(s -> s.split(",")[0], s -> Integer.parseInt(s.split(",")[1])));
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}