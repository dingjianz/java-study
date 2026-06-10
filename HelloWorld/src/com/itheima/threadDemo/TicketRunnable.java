package com.itheima.threadDemo;

public class TicketRunnable implements Runnable{
    int ticket = 0;

    @Override
    public void run() {
        // 1.循环
        while (true) {
            // 2.同步代码块（同步方法）
            if (method()) break;

            // 【修复点1】在锁外部休眠，让出CPU时间片
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 非静态方法 锁对象：当前对象this
    private synchronized boolean method() {
        if (ticket < 100) {
            // 【修复点2】锁内部不休眠，减少锁持有时间
            // 或者可以使用 Thread.yield() 主动让出CPU
            System.out.println(Thread.currentThread().getName() + "正在卖票 " + ticket);
            ticket++;
            return false;
        }
        return true;
    }

}
