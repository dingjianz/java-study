package com.itheima.mapDemo;

import java.util.*;

public class HashMapDemo3 {
    public static void main(String[] args) {
        /*
        Map集合案例-统计投票人数
           需求
            某个班级80名学生，现在需要组成秋游活动，
            班长提供了四个景点依次是(A、B、C、D),
            每个学生只能选择一个景点，
            请统计出最终哪个景点想去的人数最多。
         */

        String[] arr = {"A", "B", "C", "D"};
        ArrayList<String> list = new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < 80; i++) {
            int index = r.nextInt(arr.length);
            list.add(arr[index]);
        }

        HashMap<String, Integer> hm = new HashMap<>();

        for (String s : list) {
            if (hm.containsKey(s)) {
                int count = hm.get(s);
                count++;
                hm.put(s, count);
            } else {
                hm.put(s, 1);
            }
        }

        int max = 0;

        Set<Map.Entry<String, Integer>> entries = hm.entrySet();

        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getValue() == max) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        }

        System.out.println(hm);
    }
}
