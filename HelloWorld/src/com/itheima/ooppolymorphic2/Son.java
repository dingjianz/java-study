package com.itheima.ooppolymorphic2;

public class Son extends Father {
    String name = "儿子";
    int age = 18;

    public void erShow() {
        System.out.println("儿子的erShow方法");
    }

    @Override
    public void show() {
        System.out.println("儿子的show方法");
    }

}
