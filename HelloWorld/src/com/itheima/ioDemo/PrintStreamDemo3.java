package com.itheima.ioDemo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintStreamDemo3 {
    public static void main(String[] args) throws IOException {
    /*
        打印流的应用场景
     */

        // 获取打印流的对象，此打印流在虚拟机启动的时候，由虚拟机创建，默认指向控制台
        // 特殊的打印流，系统中的标准输出流，不能关闭，在系统中是唯一的
        PrintStream ps = System.out;

        // 调用打印流中的方法 println
        // 写出数据，自动换行，自动刷新
        ps.println("hello world");



    }
}
