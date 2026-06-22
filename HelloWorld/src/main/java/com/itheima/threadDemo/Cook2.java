package com.itheima.threadDemo;

import java.util.concurrent.ArrayBlockingQueue;

public class Cook2 extends Thread {
    ArrayBlockingQueue<String> queue;

    public Cook2(ArrayBlockingQueue<String> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        // 不断的把面条放到阻塞队别当中
        try {
            queue.put("面条");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }
    }
}
