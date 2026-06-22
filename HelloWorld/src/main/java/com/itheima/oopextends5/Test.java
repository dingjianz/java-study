package com.itheima.oopextends5;

public class Test {
    static void main(String[] args) {
        Student stu = new Student("张三", 20);

        System.out.println(stu.name); // null
        System.out.println(stu.age); // 0
    }
}
