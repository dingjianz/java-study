package com.itheima.threadDemo;

public class Test {
    public static void main(String[] args) throws InterruptedException {
     /*
                              优点                                    缺点
        继承Thread类        编程比较简单，可以直接使用Thread类中的方法      可以扩展性较差不能再继承其他的类

        实现Runnable接口    扩展性强，实现该接口的同时还可以继承其他的类     编程相对复杂，不能直接使用Thread类中的方法
        实现callable接口
      */

        /*
            Thread 多线程中常用的 api 方法
                String getName() 获取当前线程的名称
                void setName(String name) 设置当前线程的名称，构造方法也可以设置线程名称
                    如果我们没有给线程设置名字，线程也是有默认的名字的
                    格式：Thread-0 Thread-1 Thread-2...
                    new MyThread(String name): 创建实例的时候可以使用 super(name) 设置进程的名称

                void start() 启动当前线程
                static Thread currentThread() 获取当前线程
                    细节：
                        当 JVM 虚拟机启动之后，会自动启动多条线程
                        其中有一条线程就叫做 main 线程
                        他的作用就是去调用 main 方法，并执行里面的代码
                        在以前，我们写的所有代码，其实都是运行在 main 线程当中

                static void sleep(long millis) 线程休眠，单位为毫秒
                    细节：
                        1.哪条线程执行到这个方法，对应的线程就会在这里停留对应的时间
                        2.方法的参数：表示睡眠的时间，单位毫秒，例如 1000毫秒，表示睡眠 1 秒
                        3.时间到了之后，线程会自动苏醒，继续往下执行

                  setPriority(int priority) 设置当前线程的优先级 1-10
                  final int getPriority() 获取当前线程的优先级
                  final void setDaemon(boolean on) 设置当前线程为守护线程
                        守护线程：当其他非守护线程运行完毕之后，守护线程会自动结束

                  public static void yield() 线程礼让/线程出让
                  public static void join() 插入线程/插队线程
         */

        System.out.println("main 进程开始，并进入睡眠");
        Thread.sleep(3000);
        System.out.println("main 线程苏醒，继续执行");
    }
}
