package com.itheima.oopextends6;

public class Student1 extends Student {

    public Student1() {
    }

    public Student1(String name, int age, String grade) {
        super(name, age, grade);
    }

    @Override
    public void study() {
        System.out.println("攻读学士学位");
    }

}
