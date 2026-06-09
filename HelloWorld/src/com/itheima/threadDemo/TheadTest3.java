package com.itheima.threadDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TheadTest3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        多线程的第 3 种实现方式：Callable 和 Future
           特点：可以获取到多线程运行的结果

            1. 创建 MyCallable 类 实现 Callable 接口
            2. 实现 call() 方法，该方法将返回需要交给线程执行的任务
            3. 创建 MyCallable 类的对象（表示多线程要执行的任务）
            4. 创建 FutureTask 类对象，构造方法中传递 MyCallable 对象（作用管理多线程运行的结果）
            5. 创建 Thread 类对象，构造方法中传递 FutureTask 对象，并启动线程
         */

        // 1. 创建 MyCallable 类对象 (表示多线程要执行的任务)
        MyCallable mc = new MyCallable();

        // 2.创建 FutureTask 类对象 (作用管理多线程运行结果)
        FutureTask<Integer> ft = new FutureTask<>(mc);

        // 3. 创建线程的对象
        Thread t1 = new Thread(ft);

        // 4. 启动线程
        t1.start();

        // 5.获取多线程运行结果
        Integer i = ft.get();
        System.out.println(i); // 6
    }
}
