package com.itheima.oopextends;

public class Test {
    static void main() {
        Student s = new Student();

        s.name = "学生张三";
        s.age = 20;
        s.grade = 99.0;

        System.out.println(s.name + " " + s.age + " " + s.grade);

        s.eat();
        s.study();

        System.out.println("-------------------");
        Teacher t = new Teacher();
        t.name = "老师李四";
        t.age = 40;
        t.subject = "语文";
        System.out.println(t.name + " " + t.age + " " + t.subject);
        t.eat();
        t.teach();
    }
}
