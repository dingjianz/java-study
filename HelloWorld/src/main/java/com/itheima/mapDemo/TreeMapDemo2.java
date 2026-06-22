package com.itheima.mapDemo;

import java.util.StringJoiner;
import java.util.TreeMap;

public class TreeMapDemo2 {
    public static void main(String[] args) {
        /*
        统计个数
            需求:字符串"aababcabcdabcde"
            请统计字符串中每一个字符出现的次数，
            并按照以下格式输出输出结果:
            a(5)b(4)c(3)d(2)e(1)


            如果题目中没有要求对结果进行排序，默认使用 HashMap
            如果题目中要求对结果进行排序，请使用 TreeMap
         */

        TreeMap<Character, Integer> tm = new TreeMap<>();
        String str = "aababcabcdabcde";
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (tm.containsKey(c)) {
                int count = tm.get(c);
                count++;
                tm.put(c, count);
            } else {
                tm.put(c, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        tm.forEach((key, value) -> {
            sb.append(key).append("(").append(value).append(")");
        });
        System.out.println(sb);

        StringJoiner sj = new StringJoiner("", "", "");
        tm.forEach((key, value) -> {
            sj.add(key + "(" + value + ")");
        });
        System.out.println(sj);
    }
}
