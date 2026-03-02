package com.itheima.oopextends6;

public class Teacher1 extends Teacher {

    private String subject; // 学科

    public Teacher1() {
    }

    public Teacher1(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void teach() {
        System.out.println("教书（教专业课知识）");
    }
}
