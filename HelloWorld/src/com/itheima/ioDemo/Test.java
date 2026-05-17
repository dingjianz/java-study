package com.itheima.ioDemo;

public class Test {
    public static void main(String[] args) {
        /*
        1.什么是 IO 流？
            存储和读取的解决方案
          IO（Input Output）: 输入输出

        2.IO 流的作用？
         用于读写数据（本地文件、网络）

         3.IO 流按照流向可以分类哪两种流？
         输入流：文件 -> 程序
         输出流：程序 -> 文件

         4.IO 流按照数据流向可以分类哪两种流？
          字节流：可以操作所有类型的文件
          字符流：智能操作纯文本文件

          5.什么是纯文本文件？
            用系统自带的记事本打开且人能看懂的文件，例如 txt md xml lrc
         */

        /*
        IO 流体系：
            1. 字节流
                1.1 字节输入流 InputStream
                    1.1.1 FileInputStream 操作本地文件的字节输入流 可以将本地的文件中的数据读到程序中
                1.2 字节输出流 OutputStream
                    1.2.1 FileOutputStream 操作本地文件的字节输出流 可以把程序中的数据写到本地文件中

            2. 字符流
               2.1 字符输入流 Reader
                2.2 字符输出流 Writer
         */
    }
}
