package com.itheima.oopextends;

public class Student extends Person {
    // 子类：老师特有的属性
    double grade;

    public void study() {
        System.out.println(name + "在学习");
    }
}
