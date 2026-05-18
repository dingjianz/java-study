package com.itheima.ioDemo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BufferedStreamDemo1 {
    public static void main (String[] args) throws FileNotFoundException {
        /*
        需求：使用字节缓冲流完成文件的复制

        字节缓冲输入流的构造方法：
            public BufferedInputStream(InputStream is)

        字节缓冲输出流的构造方法：
            public BufferedOutputStream(OutputStream os)
         */

        // 1.创建缓冲输入字节流对象
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src/com/itheima/ioDemo/2.txt"));
    }
}
