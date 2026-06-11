package com.itheima.threadDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo1 {
    public static void main(String[] args) {
       /*

       ThreadPoolExecutor 是 Java 并发包（java.util.concurrent）中的核心类，用于创建和管理线程池。
           主要特点

           位置：java.util.concurrent.ThreadPoolExecutor

           作用：
           - 管理一组工作线程，复用线程执行提交的任务
           - 避免频繁创建/销毁线程的开销
           - 控制并发线程数量，防止资源耗尽

           核心参数
           ThreadPoolExecutor(
               int corePoolSize,        // 核心线程数
               int maximumPoolSize,     // 最大线程数
               long keepAliveTime,      // 空闲线程存活时间
               TimeUnit unit,           // 时间单位
               BlockingQueue<Runnable> workQueue,  // 任务队列
               ThreadFactory threadFactory,        // 线程工厂
               RejectedExecutionHandler handler    // 拒绝策略
           )

            参数一:核心线程数量           不能小于0
            参数二:最大线程数             不能小于等于0，最大数量>=核心线程数量
            参数三:空闲线程最大存活时间     不能小于0
            参数四:时间单位               用TimeUnit指定
            参数五:任务队列               不能为null
            参数六:创建线程工厂            不能为null
            参数七:任务的拒绝策略          不能为null
        */

        ThreadPoolExecutor executors = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(5), new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
