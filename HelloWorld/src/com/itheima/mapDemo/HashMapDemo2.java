package com.itheima.mapDemo;

import java.util.HashMap;

public class HashMapDemo2 {
    public static void main(String[] args) {
        /*
        存储学生对象并遍历
        需求
            创建一个HashMap集合，
            键是学生对象(Student)，值是籍贯(String)。
            存储三个键值对元素，并遍历
            要求:同姓名，同年龄认为是同一个学生
         */
        Student s1 = new Student("小王", 18);
        Student s2 = new Student("小李", 24);
        Student s3 = new Student("小丁", 32);
        Student s4 = new Student("小丁", 32);

        // 创建HashMap集合
        HashMap<Student, String> hm = new HashMap<>();
        hm.put(s1, "北京");
        hm.put(s2, "上海");
        hm.put(s3, "广州");
        System.out.println(hm.put(s4, "安徽")); // 广州 替换之前的s3

        for (Student key : hm.keySet()) {
            String value = hm.get(key);
            System.out.println(key.getName() + " " + key.getAge() + " " + value);
        }
    }
}
