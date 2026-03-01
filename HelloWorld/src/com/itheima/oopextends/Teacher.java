package com.itheima.oopextends;

public class Teacher extends Person {
    // 子类老师特有的属性
    String subject;

    public void teach() {
        System.out.println(name + "在教书");
    }
}
