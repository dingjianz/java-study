package com.itheima.oopjavabean;

public class BacketballCoach extends Coach {

    public BacketballCoach() {
    }

    public BacketballCoach(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("篮教练" + getName() + "教打篮球🏀");
    }
}
