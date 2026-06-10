package com.itheima.threadDemo;

public class ThreadTest4 {
    public static void main(String[] args) {
       /*
          setPriority(int priority) 设置当前线程的优先级 1-10
          final int getPriority() 获取当前线程的优先级
        */

        // 创建线程要执行的参数对象
        MyRun mr = new MyRun();

        // 创建线程对象
        Thread t1 = new Thread(mr, "飞机✈️");
        Thread t2 = new Thread(mr, "火车🚄");

        System.out.println(t1.getPriority());
        System.out.println(t2.getPriority());

        // 设置了优先级 也不一定 t1 就比t2后执行  只是概率的问题
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(10);

      /*  t1.start();
        t2.start();
*/

    }
}
