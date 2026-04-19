package com.itheima.collectionDemo;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetDemo2 {
    public static void main(String[] args) {
        /*
            TreeSet 小练习：
           创建TreeSet集合，并添加3个学生对象学生对象属性:
            姓名，年龄。
            要求按照学生的年龄进行排序
            同年龄按照姓名字母排列(暂不考虑中文)
            同姓名，同年龄认为是同一个人
         */！


        //创建TreeSet集合对象
        Student s1 = new Student("小王", 16);
        Student s2 = new Student("小张", 24);
        Student s3 = new Student("小李", 18);

        TreeSet<Student> ts = new TreeSet<>();

        // 需要在 Student 类中重写 compareTo() 方法
        // hashCode()和equals() 是和哈希表有关，和 TreeSet 无关
        ts.add(s1);
        ts.add(s2);
        ts.add(s3);

        ts.forEach(t -> System.out.println(t.getName() + " " + t.getAge()));
    }
}
