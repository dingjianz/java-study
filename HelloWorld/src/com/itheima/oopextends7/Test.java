package com.itheima.oopextends7;

import org.openjdk.jol.vm.VM;

import java.util.Scanner;


public class Test {
    static void main(String[] args) {
        /*
            子类真正能继承父类的是什么？
            1. 构造方法：不能被子类继承，可以利用super关键字调用父类的构造方法
            2. 成员变量：可以被子类继承，private私有的也可以，但是私有的无法直接调用，需要利用父类提供的公共方法来调用
            3. 成员方法：虚方法可以被子类继承
            final修饰的最终方法不能被子类继承，可以被调用
            static修饰的静态方法不能被子类继承，可以被调用
            private修饰的私有方法不能被子类继承，不能被调用
         */

        Son son = new Son();
        // 利用第三方工具查看对象在内存中的地址
        System.out.println("对象在内存中的地址：" + Long.toHexString(VM.current().addressOf(son)));
        System.out.println(son.getA());
        System.out.println(son.getB());
        System.out.println(Son.staticFather);
        // System.out.println(son.staticFather);  // Static member 'com.itheima.oopextends7.Son.staticFather' accessed via instance reference
        // 键盘录入，目的是为了程序不结束，保持在内存中，方便我们查看对象在内存中的地址
//        Scanner scanner = new Scanner(System.in);
//        scanner.nextInt();
    }
}
