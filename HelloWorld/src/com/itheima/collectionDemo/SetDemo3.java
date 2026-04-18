package com.itheima.collectionDemo;

import java.util.HashSet;

public class SetDemo3 {
    public static void main(String[] args) {
        /*
        hashSet练习1：
           需求：创建一个存储学生对象的集合，存储多个学生对象
           使用程序实现 在控制台遍历该集合

           要求：学生对象的成员变量值相同，我们就认为是同一个对象
         */
        Student s1 = new Student("小王", 18);
        Student s2 = new Student("小王", 18);
        Student s3 = new Student("张三", 32);

        // 1.创建集合用来添加学生
        HashSet<Student> set = new HashSet<>();
        System.out.println(set.add(s1)); // true
        System.out.println(set.add(s2)); // false 因为重写了 hashCode() equals()
        System.out.println(set.add(s3)); // true

        for (Student student : set) {
            System.out.println( student.getName() + "---" + student.getAge());
        }
    }
}
