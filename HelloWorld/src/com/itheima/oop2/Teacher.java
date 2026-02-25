package com.itheima.oop2;

public class Teacher {
    // 属性
    String name; // 姓名
    int age;

    // 行为
    // 能干什么？方法的形式进行体现（不加static）
    // 授课
    public void teach() {
        System.out.println("正在上课...");
    }

    // 吃饭
    public void eat() {
        System.out.println("正在吃饭...");
    }
}
