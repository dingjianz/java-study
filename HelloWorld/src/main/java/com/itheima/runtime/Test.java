package com.itheima.runtime;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        /*
        public static   Runtime getRuntime() 当前系统的运行环境对象
        public void     exit(int status) // 停止虚拟机
        public int      availableProcessors() 获得cpu的线程数
        public long     maxMemory()      JVM能从系统中获取总内存大小(单位byte)
        public long     totalMemory()     JVM已经从系统中获取总内存大小(单位byte)
        public long     freeMemory()       JvM剩余内存大小(单位byte)运行cmd命令
        public Process  exec(String command)  运行cmd命令


                 */

        Runtime runtime = Runtime.getRuntime();

        System.out.println(runtime.availableProcessors());
        System.out.println(runtime.maxMemory() / 1024 / 1024);
        System.out.println(runtime.totalMemory() / 1024 / 1024);
        System.out.println(runtime.freeMemory() / 1024 / 1024);
        
        // macOS 系统命令示例
        runtime.exec("open -a TextEdit");  // 打开文本编辑
        // runtime.exec("open .");  // 打开当前目录
        // runtime.exec("open https://www.baidu.com");  // 打开浏览器访问百度
    }
}
