package com.itheima.ioDemo;

import java.io.*;

public class BufferedStreamDemo1 {
    public static void main (String[] args) throws IOException {
        /*
        需求：使用字节缓冲流完成文件的复制

        字节缓冲输入流的构造方法：
            public BufferedInputStream(InputStream is)

        字节缓冲输出流的构造方法：
            public BufferedOutputStream(OutputStream os)
         */

        // 1.创建缓冲输入字节流对象
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src/main/java/com/itheima/ioDemo/2.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src/main/java/com/itheima/ioDemo/2_copy.txt"));

        int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        bis.close();
        bos.close();
    }
}
