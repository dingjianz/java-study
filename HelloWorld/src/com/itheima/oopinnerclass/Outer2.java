package com.itheima.oopinnerclass;

public class Outer2 {
    int a = 10;
    static int b = 20;

    static class Inner {
        final String name = "hello world";

        public void show() {
            System.out.println(b);
            // System.out.println(a); // 报错
            // 要创建对象 才能调用
            Outer2 o = new Outer2();
            System.out.println(o.a);
        }
    }
}
