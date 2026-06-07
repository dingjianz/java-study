package com.itheima.threadDemo;

public class MyThread extends Thread {
    String name;

    public MyThread() {}

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // 书写线程要执行的代码
        for (int i = 0; i < 3; i++) {
            System.out.println("hello world " + i + name);
        }
    }
}
