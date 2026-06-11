package com.itheima.threadDemo;

public class LockTest {
    public static void main(String[] args) {
        MyLock t1 = new MyLock();
        MyLock t2 = new MyLock();
        MyLock t3 = new MyLock();

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}
