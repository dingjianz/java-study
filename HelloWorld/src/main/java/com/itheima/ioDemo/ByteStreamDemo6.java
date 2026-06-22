package com.itheima.ioDemo;

import java.io.FileInputStream;
import java.io.IOException;

public class ByteStreamDemo6 {
    public static void main(String[] args) throws IOException {
     /*
     FileInputStream 一次性读取多个字节

     public int read(byte[] b) 一次读一个字节数组数据
      */

        // 1.创建对象
        FileInputStream fis = new FileInputStream("src/main/java/com/itheima/ioDemo/1.txt");

        // 2.读取数据
        byte[] b = new byte[2]; // 创建字节数组，作为缓存 2 字节
        // 一次读取多个字节数据，具体读多少，跟数组的长度有关
        // 返回值：本地文件中读取的字节数，如果返回 -1，则表示读取完毕
        int len;
        while ((len = fis.read(b)) != -1) {
            System.out.println(new String(b, 0, len));
        }

        //3.释放资源
        fis.close();
    }
}
