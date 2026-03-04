package com.itheima.oopabstract;

public abstract class Animal {
    private String name; // 名字
    private String color; // 颜色

    public Animal() {
    }

    public Animal(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getColor() {
        return color;
    }

    public String setColor(String color) {
        this.color = color;
        return color;
    }

    public abstract void eat();

    public abstract void work();

    public void drink() {
        System.out.println(name + "正在喝水...");
    }
}
