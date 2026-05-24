package com.itheima.ioDemo;

import java.io.*;

public class Test5 {
    public static void main(String[] args) throws IOException {
    /*
        4种方式拷贝文件，并统计各自用时
     */
        long currentTimeMillis = System.currentTimeMillis();

        method1();
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("方式耗时：" + (currentTimeMillis1 - currentTimeMillis) / 1000.0 + "s");
    }

    // 字节流的基本流，一次读写一个字节
    public static void method1() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\itheima\\moview.mp4");
        FileOutputStream fos = new FileOutputStream("1.mp4");
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        fos.close();
        fis.close();
    }

    // 字节流的基本流：一次读写一个字节数组
    public static void method2() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\itheima\\moview.mp4");
        FileOutputStream fos = new FileOutputStream("1.mp4");
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fis.close();
        fos.close();
    }

    // 字节流的基本流：一次读写一个字节数组
    public static void method3() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\itheima\\moview.mp4"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("1.mp4"));
        int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        bos.close();
        bis.close();
    }

    // 字节流的基本流，一次读写一个字节数组
    public static void method4() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\itheima\\moview.mp4"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("1.mp4"));
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.close();
        bis.close();
    }
}
