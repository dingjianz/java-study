package com.itheima.oopabstract;

public class Dog extends Animal {
    public Dog() {
    }

    public Dog(String name, String color) {
        super(name, color);
    }

    @Override
    public void eat() {
        System.out.println(getName() + "正在吃骨头...");
    }

    @Override
    public void work() {
        System.out.println(getName() + "正在看家...");
    }
}
