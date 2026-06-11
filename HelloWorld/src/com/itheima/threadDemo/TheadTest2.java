package com.itheima.threadDemo;

public class TheadTest2 {
    public static void main(String[] args) {
        /*
        多线程的第 2 种实现方式
            1. 定义一个类实现 Runnable接口
            2. 重写run方法，将线程要执行的代码写在run方法中
            3. 创建一个上述类的实例
            4. 创建Thread类的实例
            5. 调用start方法启动线程
         */

        MyRunnable mr = new MyRunnable();

        Thread t1 = new Thread(mr);
        t1.setName("线程1");

        Thread t2 = new Thread(mr);
        t2.setName("线程2");

        t1.start();
        t2.start();

    }
}
