package com.itheima.threadDemo;

public class TheadTest {
    public static void main(String[] args) {
        /*
        多线程的第一种启动方式
            1. 创建一个类继承Thread类
            2. 重写run方法，将线程要执行的代码写在run方法中
            3. 创建Thread类的实例
            4. 调用start方法启动线程
         */

        MyThread mt = new MyThread("线程1");
        MyThread mt2 = new MyThread("线程2");
        // 调用start方法启动线程
        mt.start();
        mt2.start();
    }
}
