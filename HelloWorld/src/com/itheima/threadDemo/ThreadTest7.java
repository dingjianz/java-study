package com.itheima.threadDemo;

public class ThreadTest7 {
    public static void main(String[] args) {
        /*
           微信中的抢红包
            假设:100块，分成了3个包，现在有5个人去抢。
            其中，红包是共享数据。
            5个人是5条线程。
            打印结果如下:
                XXx抢到了XXX元
                XXx抢到了XXX元
                XXX抢到了XXX元
                XXX没抢到
                XXX没拾到
         */

        // 100块 = 10000分，分成3个包，红包是共享数据
        RedPacket redPacket = new RedPacket(10000, 3);

        // 5个人 = 5条线程，去抢同一个红包
        Thread t1 = new Thread(redPacket, "张三");
        Thread t2 = new Thread(redPacket, "李四");
        Thread t3 = new Thread(redPacket, "王五");
        Thread t4 = new Thread(redPacket, "赵六");
        Thread t5 = new Thread(redPacket, "钱七");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
