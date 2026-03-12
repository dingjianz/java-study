package com.itheima.arraydemo;

import java.util.ArrayList;

public class Test2 {
    public static void main() {
    /*
    包装类：
        基本数据类型对应的包装类有：
        byte -> Byte
        short -> Short
        int -> Integer
        long -> Long
        char -> Character
        float -> Float
        double -> Double
        boolean -> Boolean

        添加数字并遍历
        需求:定义一个集合，添加数字，并进行遍历。
        遍历格式参照:[元素1，元素2，元素3]。
     */

//        Integer a = 1;
//        Integer b = 2;
//        Integer c = 3;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1); // 自动装箱（Autoboxing）
        // list.add(Integer.valueOf(1));  // 自动装箱成 Integer 对象
        list.add(2);
        list.add(3);

        int num = list.get(0); // 自动拆箱（Autounboxing）

        System.out.print(list);

    }
}
