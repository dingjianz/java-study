package com.itheima.ioDemo;

import java.io.*;

public class BufferedStreamDemo4 {
    public static void main (String[] args) throws IOException {
        /*
        字符缓冲输出流
         构造方法：
            public BufferedWriter(Writer r)

         特有方法：
            public void newLine() 跨平台的换行方法 windows mac Linux 等
         */

        // 1.创建字符缓冲输出流对象  续写的时候 要写在 FileWriter 里面
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/itheima/ioDemo/2.txt", true));

        // 2.写出数据
        bw.write("hello,world");
        bw.newLine();
        bw.write("你好世界");

        // 3.释放资源
        bw.close();

    }
}
