package com.itheima.oop5;

public class Test {
   public static void main(String[] args) {
        Student s1 = new Student("张三", 20);
        Student s2 = new Student("李四", 22);

        System.out.println("s1的老师是：" + Student.teacherName);
        System.out.println("s2的老师是：" + Student.teacherName);

        // 修改s1的老师
       Student.teacherName = "王老师";
        System.out.println("修改s1的老师后：");
        System.out.println("s1的老师是：" + Student.teacherName);
        System.out.println("s2的老师是：" + Student.teacherName);
    }
}
