package com.itheima.oopextends6;

public class Student2 extends Student {

    public Student2() {
    }

    public Student2(String name, int age, String grade) {
        super(name, age, grade);
    }

    @Override
    public void study() {
        System.out.println("攻读硕士学位");
    }


    @Override
    public void sleep() {
        System.out.println("硕士研究生住宿升级，在豪华版学生公寓睡觉");
    }
}
