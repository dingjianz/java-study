package com.itheima.streamDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) {
        /*
        Stream流的作用：结合了Lambda表达式，简化集合、数组的操作

        Stream流使用步骤：
        1.先得到一个Stream流，并把数据放上去
        2.对Stream流进行操作
           过滤 转换 (中间方法) 方法调用完毕之后，还可以调用其他方法
           统计 打印 (终止方法) 最后一步，调用完毕之后，不能再调用其他方法


       获取方式    方法名                                          说明
        单列集合  default Stream<E> stream()                     Collection中的默认方法
        双列集合   无                                            无法直接使用Stream流,需要先转成单列集合 keySet entrySet
        数组     public static <T> Stream<T> of(T... values)     Arrays类中的静态方法
        一堆零散数据 public static <T> Stream<T> of(T ...values)  Stream类中的静态方法
         */

        // 1.单列集合获取Stream流
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张三","张三丰","张翠山","张无忌");
        // limit(2) 的作用是截断流，只保留前2个元素。
        // 这里先过滤出以"张"开头的名字，然后取前2个进行打印。
        list.stream()
                .filter(s -> s.startsWith("张")) // 中间操作：过滤
                .limit(2)                        // 中间操作：截断，只取前2个
                .forEach(System.out::println);   // 终止操作：遍历打印

        System.out.println("--------------------------");

        // 2.双列集合获取Stream流
        HashMap<String,String> map = new HashMap<>();
        map.put("张三","张三");
        map.put("张三丰","张三丰");
        map.put("张翠山","张翠山");
        map.put("张无忌","张无忌");
        map.keySet().stream().forEach(System.out::println);
        map.entrySet().stream().forEach(System.out::println);

        System.out.println("--------------------------");

        //3.数组获取Stream流
        String[] arr = {"张三","张三丰","张翠山","张无忌"};
        Arrays.stream( arr).forEach(System.out::println);

        System.out.println("--------------------------");

        //4.一堆零散数据获取Stream流
        Stream.of("张三","张三丰","张翠山","张无忌").forEach(System.out::println);
    }
}
