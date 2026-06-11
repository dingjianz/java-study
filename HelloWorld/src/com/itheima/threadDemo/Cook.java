package com.itheima.threadDemo;

public class Cook extends Thread{
    @Override
    public void run() {
        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {
                    break;
                } else {
                    // 先判断桌子是否有食物
                    if (Desk.foodFlag == 1) {
                        // 桌子上有食物
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        // 桌子上没有食物
                        Desk.foodFlag = 1;
                        System.out.println("厨师正在做面条，做好之后通知吃货吃面条！！！");
                        Desk.lock.notifyAll();
                    }
                }
            }
        }
    }
}
