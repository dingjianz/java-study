package com.itheima.oopinnerclass;

public class Outer4 {
    public void show() {
        Swim swim = new Swim() {
            @Override
            public void swim() {
                System.out.println("重写swim方法");
            }
        };

        Person person = new Person("张三") {

        };
    }
}
