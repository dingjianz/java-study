package com.itheima.ioDemo;

import java.io.FileWriter;
import java.io.IOException;

public class CharStreamDemo3 {
    public static void main(String[] args) throws IOException {
        /*
        字符流输出对象FileWriter
        1.创建字符流输出对象
          public FileWriter(File file) 创建字符输出流对象关联本地文件
          public FileWriter(String fileName) 创建字符输出流对象关联本地文件
          public FileWriter(File file, boolean append) 创建字符输出流对象关联本地文件 续写
          public FileWriter(String fileName, boolean append) 创建字符输出流对象关联本地文件 续写

        2.调用方法，完成数据的输出
          public void write(int c) 写出一个字符
          public void write(String str) 写出一个字符串
          public void write(String str, int off, int length) 写出一个字符串的一部分
          public void write(char[] cbuf) 写出一个字符数组
          public void write(char[] cbuf, int off, int len) 写出一个字符数组的一部分
        */

        FileWriter fw = new FileWriter("src/main/java/com/itheima/ioDemo/1.txt", true);

        // 根据字符集的编码方式进行编码，把编码之后的数据写到文件中去
        // UTF-8
        // fw.write(25105); // 我
        fw.write("我爱你？？");// 用的最多

        char[] chs = {'a', 'b', 'c', '喔'};
        fw.write(chs);


        fw.close();

    }
}
