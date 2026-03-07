package com.itheima.oopinnerclass;

public class Outer {
    private  final int a = 10;

    // 成员内部类：定义在成员位置（方法外面）
    class Inner {
        private final int A = 20;

        public void show() {
            int a = 30;
            System.out.println(a);          // 30 局部变量
            System.out.println(this.a);     // 20 内部类的成员变量
            System.out.println(Outer.this.a); // 10 外部类的成员变量
        }
    }
}
