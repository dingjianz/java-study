package com.itheima.mapDemo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapDemo {
    /*
    LinkedHashMap集合特点
        由键决定：有序、无索引、不重复
        这里的有序说的是保证存储和取出的元素顺序一致
        原理：底层还是是哈希表结构，只是每个键值对元素又额外多了一个双链表的机制记录存储的顺序。
     */
    public static void main(String[] args) {
        //创建LinkedHashMap集合
        LinkedHashMap<String, String> hm = new LinkedHashMap<>();

        //添加元素
        hm.put("张三", "23");
        hm.put("王五", "25");
        hm.put("赵六", "26");
        hm.put("田七", "27");

        //遍历
        Set<Map.Entry<String, String>> entrySet = hm.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}
