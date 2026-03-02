package com.itheima.oopextends5;

public class Student {
    String name;
    int age;

    public Student() {
        // this(); // this()调用本类的其他构造方法
        // 如果构造方法中写了this(),就不能再写super()了，因为super()和this()都必须在构造方法的第一行调用。
        this("学生", 18); // 调用本类的有参构造方法，必须在构造方法的第一行调用本类的构造方法。
        System.out.println("学生的无参构造方法被调用了");
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("学生的有参构造方法被调用了");
    }
}
