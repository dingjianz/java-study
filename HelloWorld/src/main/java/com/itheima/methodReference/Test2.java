package com.itheima.methodReference;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) {
       /*
            方法引用-- 引用静态方法
                格式： 类名::静态方法名


             需求：
                集合中有以下数字，要求将他们都变成int类型
                    "1", "2", "3", "4", "5
        */
        String[] arr = {"1", "2", "3", "4", "5"};

        List<Integer> integerStream = Arrays.stream(arr).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(integerStream);
        System.out.println(integerStream.get(0) instanceof Integer); // true

    }
}
