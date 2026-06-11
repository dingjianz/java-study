package com.itheima.threadDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock extends Thread {
    static int ticket = 0;

    // true 表示公平锁：按线程等待顺序排队拿锁，避免某个线程一直插队导致其他线程饿死
    static Lock lock = new ReentrantLock();

    @Override
    public void run() {
        // 1.循环
        while (true) {
            // 加锁
            lock.lock();
            try {
                // 2.判断
                if (ticket < 100) {
                    // 还有票，卖一张
                    ticket++;
                    System.out.println(getName() + " 正在卖第 " + ticket + " 张票");
                } else {
                    // 票卖完，退出循环
                    break;
                }
            } finally {
                // 无论是否异常都要释放锁，否则其他线程永远拿不到锁
                lock.unlock();
            }

            // sleep 放在锁外面：卖完一张就释放锁并休眠，给其他线程抢锁的机会
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
