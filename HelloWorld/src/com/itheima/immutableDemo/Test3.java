package com.itheima.immutableDemo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test3 {
    public static void main(String[] args) {
        /*
        创建不可变的Map集合
         */

        // Map.of 参数最多为 10 个
        Map<String,Integer> map = Map.of("张三", 20, "李四", 21, "王五", 22);
        System.out.println(map);

        // ofEntries的参数可以为多个
        /*
        Map<String,Integer> map2 = Map.ofEntries(
                Map.entry("张三1", 20),
                Map.entry("李四2", 21),
                Map.entry("王五3", 22),
                Map.entry("张三4", 20),
                Map.entry("李四5", 21),
                Map.entry("王五6", 22),
                Map.entry("张三7", 20),
                Map.entry("李四8", 21),
                Map.entry("王五9", 22),
                Map.entry("张三10", 20),
                Map.entry("李四11", 21),
                Map.entry("王五12", 22)
        );
         */

        HashMap<String,Integer> map2 = new HashMap<>();
        map2.put("张三1", 20);
        map2.put("李四2", 21);
        map2.put("王五3", 22);
        map2.put("张三4", 20);
        map2.put("李四5", 21);
        map2.put("王五6", 22);
        map2.put("张三7", 20);
        map2.put("李四8", 21);
        map2.put("王五9", 22);
        map2.put("张三10", 20);
        map2.put("李四11", 21);
        map2.put("王五12", 22);

        /*
            Set<Map.Entry<String,Integer>> entries = map2.entrySet();
            Map.Entry[] arr =  entries.toArray(new Map.Entry[0]);
            Map map1 = Map.ofEntries(arr);
         */

        /*
        toArray 方法在底层会比较集合的长度和数组的长度两者的大小
            如果集合的长度 > 数组的长度，则创建一个长度为集合长度的数组
            如果集合的长度 <= 数组的长度，数据在数组中的放的下，此时不会创建新的数据，而是直接用
         */
        // 简写
        Map map1 = Map.ofEntries(map2.entrySet().toArray(new Map.Entry[0]));
        System.out.println(map1);


        // Map.copyOf JDk10 之后的版本
        Map map3 = Map.copyOf(map2);

    }
}
