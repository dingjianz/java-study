package com.itheima.threadDemo;

import java.util.concurrent.ArrayBlockingQueue;

public class WaitAndNotify2 {
    public static void main(String[] args) {
        /*
        需求：利用阻塞队列完成生产者和消费者（等待唤醒机制）的代码

        细节：生产和和消费者必须使用同一个阻塞队列
         */

        // capacity 就是这个队列最多能装多少个元素，是一个固定的"上限"。
        // 设成 1，就是经典的"厨师做一碗、顾客吃一碗"的节奏
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        Cook2 cook = new Cook2(queue);
        Foodie2 foodie = new Foodie2(queue);
        cook.start();
        foodie.start();

    }
}
