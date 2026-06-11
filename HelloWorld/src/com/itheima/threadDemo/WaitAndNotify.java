package com.itheima.threadDemo;

public class WaitAndNotify {
    public static void main(String[] args) {
        /*
        需求：完成生产者和消费者（等待唤醒机制）的代码
                实现线程轮流交替执行的效果
         */

        // 创建线程对象
        Cook cook = new Cook();
        Foodie foodie = new Foodie();

        cook.setName("厨师");
        foodie.setName("吃货");

        cook.start();
        foodie.start();
    }
}
