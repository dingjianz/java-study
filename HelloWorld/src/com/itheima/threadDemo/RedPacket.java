package com.itheima.threadDemo;

import java.util.Random;

public class RedPacket implements Runnable {
    // 红包总金额（单位：分），用分避免浮点数精度问题
    private int totalMoney;
    // 红包个数
    private int count;

    private final Random random = new Random();

    public RedPacket(int totalMoney, int count) {
        this.totalMoney = totalMoney;
        this.count = count;
    }

    @Override
    public void run() {
        // 红包是共享数据，多个线程同时抢，需要加锁
        synchronized (this) {
            String name = Thread.currentThread().getName();

            // 红包已经被抢完
            if (count == 0) {
                System.out.println(name + "没抢到");
                return;
            }

            int money; // 本次抢到的金额（单位：分）
            if (count == 1) {
                // 最后一个红包，把剩余金额全部给它
                money = totalMoney;
            } else {
                // 限制最大金额，给后面每个红包至少留1分
                int maxMoney = totalMoney - (count - 1);
                money = random.nextInt(maxMoney) + 1; // +1 避免红包为0
            }

            // 扣除已抢金额和个数
            totalMoney -= money;
            count--;

            System.out.println(name + "抢到了" + (money / 100.0) + "元");
        }
    }
}
