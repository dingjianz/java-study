package com.itheima.threadDemo;

import java.util.concurrent.ArrayBlockingQueue;

public class Foodie2 extends Thread {
    ArrayBlockingQueue<String> queue;

    public Foodie2(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            // 不断从阻塞队列中获取面条
            try {
                String food = queue.take();
                System.out.println(food);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
