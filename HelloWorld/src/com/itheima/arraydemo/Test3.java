package com.itheima.arraydemo;

import java.util.ArrayList;

public class Test3 {
    public static void main(String[] args) {
        /*
        添加学生对象并遍历

        需求：定义一个集合，添加一些学生对象
        学生类的属性为：id，姓名，年龄。

        要求：
            1. 遍历集合，将所有学生的属性打印在控制台上，每个学生一行
            2. 定义一个方法，根据id查找学生的信息。
               存在：返回索引
               不存在：返回-1
         */

        ArrayList<Student> list = new ArrayList<>();
        Student s1 = new Student(1001, "林青霞", 30);
        Student s2 = new Student(1002, "张曼玉", 35);
        list.add(s1);
        list.add(s2);

        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            System.out.println(s.getId() + "," + s.getName() + "," + s.getAge());
        }

        Student s = findStudentById(list, 1001);
        if (s != null) {
            System.out.println(s.getId() + "," + s.getName() + "," + s.getAge());
        } else {
            System.out.println("没有找到该学生");
        }
    }

    public static Student findStudentById(ArrayList<Student> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}
