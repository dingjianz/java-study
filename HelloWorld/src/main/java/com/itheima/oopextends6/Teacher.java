package com.itheima.oopextends6;

public class Teacher extends Person {
    public Teacher() {
    }

    public Teacher(String name, int age) {
        super(name, age);
    }

    public void teach() {
        System.out.println("教书（教通识课知识）");
    }
}
