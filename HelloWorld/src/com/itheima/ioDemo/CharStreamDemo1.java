package com.itheima.ioDemo;

import java.io.FileReader;
import java.io.IOException;

public class CharStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*
        1. 创建字符输入流对象
        public FileReader(File file) 创建字符输入流对象关联本地文件
        public FileReader(String fileName) 创建字符输入流对象关联本地文件

        2. 使用字符输入流对象读取数据
            字符流的底层也是字节流，默认也是一个字节一个字节的读取数据
            如果遇到中文就会一次性读取多个字节，GBK一次读取两个字节，UTF-8一次读三个字节。
            英文：文件里面二进制数据 0110 0001
            read方法读取之后，解码并转成十进制97

            中文：文件里面二进制数据 1100 0101 1100 0110
            read方法读取之后，解码并转成十进制16548
            如果想看到中文汉字，就是把这些十进制数据进行强转为char类型

        public int read()  读取一个字符返回
        public int read(char[] buffer)  读取多个字符返回


        4. 释放资源
        fos.close();
         */

        // 1. 创建字符输入流对象
        FileReader fis = new FileReader("src/com/itheima/ioDemo/1.txt");

        // 2.读取数据
        int c = fis.read();
        while (c != -1) {
            System.out.print((char) c);
            c = fis.read();
        }

        fis.close();
    }
}
