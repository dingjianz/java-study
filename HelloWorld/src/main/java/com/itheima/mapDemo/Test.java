package com.itheima.mapDemo;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        /*
        双列集合的特点
            1.双列集合一次需要存一对数据，分别为键和值
            2.键不能重复，值可以重复
            3.键和值是一一对应的，每一个键只能找到自己对应的值
            4.键+值这个整体我们称之为"键值对"或者"键值对对象"，在Java中叫做"Entry对象"

        Map是双列集合的顶层接口，它的功能是全部双列集合都能继承使用的
        常用的api包括
           V put(K key, V value) 添加元素，如果当前key有值，则替换掉，并返回旧值，如果没有，则返回null
           V get(Object key) 获取元素
           V remove(Object key) 删除元素 返回被删除的元素
           boolean containsKey(Object key) 判断集合中是否包含指定的键
           boolean containsValue(Object value) 判断集合中是否包含指定的值
           boolean isEmpty() 判断集合是否为空
           int size() 获取集合的元素个数
           void clear() 清空集合
         */

        Map<String, String> map = new HashMap<>();
        map.put("郭靖", "黄蓉");
        map.put("韦小宝", "沐剑屏");
        map.put("杨过", "小龙女");

        System.out.println(map); // {韦小宝=沐剑屏, 杨过=小龙女, 郭靖=黄蓉}

        System.out.println(map.get("韦小宝")); // 沐剑屏

        map.put("韦小宝", "双儿");

        System.out.println(map);

        System.out.println(map.containsKey("韦小宝")); // true

        System.out.println(map.containsValue("小龙女")); // true

        System.out.println(map.remove("韦小宝")); // 双儿

        System.out.println(map.containsKey("韦小宝")); // false

        System.out.println(map); // {杨过=小龙女, 郭靖=黄蓉}
    }
}
