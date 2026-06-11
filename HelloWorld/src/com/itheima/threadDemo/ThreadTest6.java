package com.itheima.threadDemo;

public class ThreadTest6 {
    public static void main(String[] args) {
        /*
            同步方法：synchronized 修饰的方法，称为同步方法
            格式：修饰符 synchronized 返回值类型 方法名(参数列表){}

            特点1:同步方法是锁住方法里面所有的代码
            特点2:锁对象不能自己指定，锁对象是固定的
              锁对象：非静态方法：锁对象是当前对象this
                    静态方法：锁对象是当前类的对象，也就是当前类的字节码文件对象

         */

        /*
            需求：电影院 三个 窗口卖 100 张票 使用同步方法实现
         */

        TicketRunnable tr = new TicketRunnable();
        Thread t1 = new Thread(tr);
        Thread t2 = new Thread(tr);
        Thread t3 = new Thread(tr);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
