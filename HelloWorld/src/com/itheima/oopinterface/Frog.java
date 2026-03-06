package com.itheima.oopinterface;

public class Frog extends Animal implements Swim {
    public Frog() {
    }

    public Frog(String name, String color) {
        super(name, color);
    }

    @Override
    public void eat() {
        System.out.println("🐸" + getName() + "正在吃虫子...");
    }

    @Override
    public void swim() {
        System.out.println("🐸" + getName() + "正在蛙泳...");
    }
}
