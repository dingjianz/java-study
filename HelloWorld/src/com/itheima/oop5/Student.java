package com.itheima.oop5;

public class Student {
    // static: static修饰的成员变量和成员方法属于类本身，而不是某个对象。它们在内存中只有一份，所有对象共享这份数据。
    /*
        static修饰成员变量
        特点：叫做静态变量，被该类所有对象共享，内存中只有一份。
        随着类的加载而加载，随着类的卸载而卸载，优于对象的生命周期。
        访问：可以通过对象访问，也可以通过类名访问。推荐通过类名访问。

        static修饰成员方法
        特点：叫做静态方法，被该类所有对象共享，内存中只有一份。
        该方法多用在测试类中，或者工具类中。Javabean中不建议使用static修饰成员方法。
     */
    static String teacherName = "张老师";
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
}
