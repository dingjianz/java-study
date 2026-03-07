package com.itheima.oopjavabean;

public class PingpangCoach extends Coach implements Skill {
    public PingpangCoach() {
    }

    public PingpangCoach(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("乒乓球教练" + getName() + "教打乒乓球🏓");
    }

    @Override
    public void speakEnglish() {
        System.out.println("乒乓球教练🏓" + getName() + "说英语");
    }

}
