package com.itheima.threadDemo;

public class TicketThread extends Thread {
    static private int ticket = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (TicketThread.class) {
                if (ticket < 11) {
                    System.out.println(getName() + " 正在卖票 " + ticket);
                    ticket++;
                } else {
                    break;
                }
            }
            // sleep 放在锁外面：卖完一张就释放锁并休眠，给其他线程抢锁的机会
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
