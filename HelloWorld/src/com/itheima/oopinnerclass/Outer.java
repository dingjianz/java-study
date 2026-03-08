package com.itheima.oopinnerclass;

public class Outer {
    int a = 10;
    String name = "1212";

    public Outer(String name) {
        this.name = name;
    }

    // 成员内部类：定义在成员位置（方法外面）
    class Inner {
        int a = 20;
        // static String name; // 新版本jdk 支持 内部类静态成员变量

        public void show() {
            int a = 30;
            // 如果有重名的按照下面的来 如果没重名 直接取 a 就行
            System.out.println(a);          // 30 局部变量
            System.out.println(this.a);     // 20 内部类的成员变量
            System.out.println(Outer.this.a); // 10 外部类的成员变量
        }
    }
}
