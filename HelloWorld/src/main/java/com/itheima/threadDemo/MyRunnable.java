package com.itheima.threadDemo;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread(); // 获取当前线程的对象
        for (int i = 0; i < 3; i++) {
            System.out.println("Runnable " + i + "   " + t.getName());
        }
    }
}
