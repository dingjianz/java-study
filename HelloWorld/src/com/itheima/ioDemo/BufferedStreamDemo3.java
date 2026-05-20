package com.itheima.ioDemo;

import java.io.*;

public class BufferedStreamDemo3 {
    public static void main (String[] args) throws IOException {
        /*
        字符缓冲输入流
         构造方法：
            public BufferedReader(Reader r)

         特有方法：
            public String readLine()
            readLine方法在读取的时候，一次读一整行，遇到回车换行结束
            但是不会把回车换行读到内存中去
         */

        // 1.创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(new FileReader("src/com/itheima/ioDemo/1.txt"));
        String line = null;
        while ((line = br.readLine())!= null) {
            System.out.println(line);
        }
        br.close();
    }
}
