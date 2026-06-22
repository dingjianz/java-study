package com.itheima.oopextends4;

public class Father {
    String name = "父亲";
    int age = 40;

    public Father() {
        System.out.println("父亲的无参构造方法被调用了");
    }

    public Father (String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("父亲的有参构造方法被调用了");
    }
}
