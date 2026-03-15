package com.itheima.Objectdemo;

import java.util.ArrayList;

public class Student implements Cloneable {
    private String name;
    private int age;

    private ArrayList<Integer> arrayList;

    public Student() {
    }

    public Student(String name, int age, ArrayList<Integer> arrayList) {
        this.name = name;
        this.age = age;
        this.arrayList = arrayList;
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

    public ArrayList<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
