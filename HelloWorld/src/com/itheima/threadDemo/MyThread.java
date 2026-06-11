package com.itheima.threadDemo;

public class MyThread extends Thread {
    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        // 书写线程要执行的代码
        for (int i = 0; i < 3; i++) {
            System.out.println("hello world " + i + "   " + getName());
        }
    }
}
