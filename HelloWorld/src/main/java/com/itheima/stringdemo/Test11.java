package com.itheima.stringdemo;

import java.util.StringJoiner;

public class Test11 {
    public static void main(String[] args) {
        /*
            StringJoiner类：字符串连接器, jdk8 新增api

            创建 StringJoiner 对象
            StringJoiner sj = new StringJoiner(delimiter, prefix, suffix);
            delimiter：分隔符
            prefix：前缀
            suffix：后缀
         */
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        sj.add("张三");
        sj.add("李四").add("王五");
        System.out.println(sj.length()); // 12
        System.out.println(sj); // [张三, 李四, 王五]

        StringJoiner sj2 = new StringJoiner("-", "【", "】");
        sj2.add("hello").add("world");
        System.out.println(sj2); // 【hello-world】
    }
}
