package com.itheima.ooppolymorphic3;

public class Person {
    private String name; // 姓名
    private int age; // 年龄
    private String gender; // 性别

    public Person() {
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void useTool(Tool tool) {
        System.out.println(name + "正在使用" + tool.getBrand() + "交通工具...");
        tool.move();
    }
}
