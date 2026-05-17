package com.itheima.ioDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo5 {
    public static void main(String[] args) throws IOException {
        /*
          练习：文件拷贝
            把 D:\itheima\moview.mp4 文件拷贝到 当前模块下

            注意：选择一个比较小的文件，不要太大。大文件拷贝我们下一个视频说。

            课堂作业：统计一下拷贝时间，单位毫秒

         */
        FileInputStream fis = new FileInputStream("D:\\itheima\\moview.mp4");
        FileOutputStream fos = new FileOutputStream("copy.mp4");
        long start = System.currentTimeMillis();
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        long end = System.currentTimeMillis();
        System.out.println("拷贝完成，耗时：" + (end - start) + "毫秒");
        fos.close();
        fis.close();

    }
}
