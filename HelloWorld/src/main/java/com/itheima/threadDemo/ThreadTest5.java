package com.itheima.threadDemo;

public class ThreadTest5 {
    public static void main(String[] args) {
        /*
            电影院 三个 窗口卖 100 张票

            同步代码块：把操作共享数据的代码锁起来

            特点：锁默认打开，有一个线程进去了，锁自动关闭
                  里面的代码全部执行完毕，线程出来，锁自动打开
         */

        TicketThread t1 = new TicketThread();
        TicketThread t2 = new TicketThread();
        TicketThread t3 = new TicketThread();
        t1.start();
        t2.start();
        t3.start();
    }
}
