package com.itheima.oopextends6;

public class Student extends Person {
    private String grade;

    public Student() {
    }

    public Student(String name, int age, String grade) {
        super(name, age); // 调用父类的构造方法
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void study() {
        System.out.println("学习");
    }

}
