package com.itheima.oop4;

public class Student {
    /*
        构造方法
            1. 构造方法也是一种特殊的方法
            2. 构造方法的名称必须和类名完全一样，大小写也要一样
            3. 构造方法没有返回值类型，连void都没有
            4. 构造方法可以有参数，也可以没有参数
            5. 如果没有编写任何构造方法，编译器会默认提供一个无参的构造方法
            6. 一旦编写了至少一个构造方法，编译器就不再提供默认的无参构造方法了

         执行时机
            1. 构造方法是在创建对象时被自动调用的，由虚拟机调用，不能手动调用构造方法
            2. 构造方法的主要作用是初始化对象的成员变量
     */

    String name;
    int age;
    char gender;
    double height;

    public Student () {
        System.out.println("这是一个无参的构造方法");
    }

    public Student (String name, int age, char gender, double height) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        System.out.println("这是一个有参的构造方法，参数name的值是：" + name);
        System.out.println(this.name + " 今年" + this.age + "岁了，性别是" + this.gender + "，身高是" + this.height + "米");
    }
}
