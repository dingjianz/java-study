package com.itheima.oopjavabean;

public class PingpangPlayer extends Player implements Skill {

    public PingpangPlayer() {
    }

    public PingpangPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void study() {
        System.out.println("乒乓球运动员" + getName() + "学打乒乓球🏓");
    }

    @Override
    public void speakEnglish() {
        System.out.println("乒乓球运动员说英语");
    }
}
