package com.itheima.oopinnerclass;

public class Outer3 {

    public void show() {
        class Inner {
            String name;
            int age;

            public void method() {
                System.out.println(name);
                System.out.println(age);
                System.out.println("局部内部类中的method方法");
            }

            public static void method2() {
                System.out.println("局部内部类中的method2静态方法");
            }
        }

        Inner i = new Inner();
        System.out.println(i.name);
        System.out.println(i.age);
        i.method();
        Inner.method2();
    }
}
