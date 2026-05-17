package com.itheima.ioDemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteStreamDemo3 {
    public static void main(String[] args) throws IOException {
        /*
            FileInputStream 操作本地文件的字节输入流
            可以把本地文件中的数据读取到程序中来
            细节1: 如果文件不在，就直接报错
            细节2:读取数据的时候，一次读一个字节，读出来的是数据再ASCII上对应的数字

         */

        // 1. 创建字节输入流对象
        FileInputStream fis = new FileInputStream("src/com/itheima/ioDemo/1.txt");

        // 2. 读数据
        /*int b = fis.read();
        System.out.println((char)b);*/
        while(true) {
            int b = fis.read();
            if(b == -1) {
                break;
            }
            System.out.print((char)b);
        }
        

        // 3. 释放资源
        fis.close();
    }
}
