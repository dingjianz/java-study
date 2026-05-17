package com.itheima.ioDemo;

import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*
         演示：字节输出流 FileOutputStream
         实现需求：写出一段文字到本地文件中，（暂时不写中文）

        1.创建字节输出流对象
         细节1:参数是字符串中表示的路径 或者是 File对象
         细节2: 如果文件不存在会创建一个新的文件，但是要保证父级路径是存在的
         细节3: 如果文件存在，会清空文件再写出

        2.写数据
            细节：write方法的参数是整数，但是实际上写到本地文件中的数据是整数在ASCII码表中对应的字符
            97 -> a
            100 -> d

        3.释放资源
            每次创建完一个流对象后，必须释放，否则会造成内存泄漏


            void write(int b) throws IOException // 写一个字节
            void write(byte[] b) throws IOException // 写字节数组
            void write(byte[] b, int off, int len) throws IOException // 写字节数组的一部分

         */

        // 1. 创建字节输出流对象
        FileOutputStream fos = new FileOutputStream("src/com/itheima/ioDemo/1.txt");

        // 2. 写数据
         /*fos.write(55); // 7
        fos.write(56); // 8
        fos.write(57); // 9*/


        byte[] bytes = {97, 98, 99, 100, 101};
        /*fos.write(bytes); // abcde*/

        fos.write(bytes, 1, 3);

        // 3. 释放资源
        fos.close();


    }
}
