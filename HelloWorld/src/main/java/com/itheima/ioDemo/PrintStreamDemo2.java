package com.itheima.ioDemo;

import java.io.*;

public class PrintStreamDemo2 {
    public static void main(String[] args) throws IOException {
    /*
    字符打印流
        构造方法：
            public PrintWriter(Write/File/String) 关联字节输出流/文件/文件路径
            public PrintWriter(String fileName, String charsetName)  指定字符编码
            public PrintWriter(Write out, boolean autoFlush)  自动刷新
            public PrintWriter(Write out, boolean autoFlush, String charsetName) 指定字符编码和自动刷新

       成员方法：
            public void write(int b) 常规方法：规则和之前一样，将指定的字节写出
            public void print(String s) 特有方法：打印任意数据，不换行
            public void println(String s) 特有方法：打印任意数据，自动刷新，自动换行
            public void printf(String format, Object... args) 特有方法：带有占位符的打印语句，不换行

     */

        // 1.创建字节打印流的对象
        PrintWriter pw = new PrintWriter(new FileOutputStream("src/main/java/com/itheima/ioDemo/1.txt"), true);
        // 2.写数据
        // println: 自动刷新，自动换行, 原数据 写到方法的是97 到文件中也是97
        pw.println(97);
        pw.println(98);

        // print: 不自动刷新，不自动换行, 原数据 写到方法的是1 到文件中也是1
        pw.print(1);
        pw.print("hello world");

        // printf：不自动刷新，不自动换行, 原数据
        pw.printf("%s爱%n上了%s", "阿珍", "阿强");
        pw.printf("%d", 97);
        // pw.flush(); // 刷新缓存，确保数据写入文件
        pw.close();
    }
}
