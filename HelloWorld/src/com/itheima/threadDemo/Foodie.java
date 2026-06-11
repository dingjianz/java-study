package com.itheima.threadDemo;

public class Foodie extends Thread {
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {
                    break;
                } else {
                    // 先判断桌子上是否有面条
                    if (Desk.foodFlag == 1) {
                        // 桌子上有面条
                        // 把吃的总数-1
                        Desk.count--;
                        System.out.println("吃货正在吃面条，还能再吃 " + Desk.count + " 碗！！！" );
                        // 吃完之后，唤醒厨师继续做
                        Desk.lock.notifyAll(); // 唤醒和当前锁对象相关的所有线程
                        // 修改桌子的状态
                        Desk.foodFlag = 0;
                    } else {
                        // 桌子上没有面条
                        try {
                            Desk.lock.wait(); // 让当前线程跟锁进行绑定
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
