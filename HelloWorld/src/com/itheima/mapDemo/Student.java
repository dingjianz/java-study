package com.itheima.mapDemo;

import java.util.Objects;

public class Student implements Comparable<Student>{
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public int compareTo(Student o) {
        /*
        返回值:
            负数:表示当前要添加的元素是小的，存左边
            正数:表示当前要添加的元素是大的，存右边
            0:表示当前要添加的元素已经存在，舍弃
         */
       int num = 0;
        // this表示当前要添加的元素
        // o表示已经存在红黑树的元素
        num = this.age - o.age;
        num = num == 0 ? this.name.compareTo(o.name) : num;
       return num;
    }
}
