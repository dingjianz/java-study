package com.itheima.ioDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test3 {
    public static void main(String[] args) throws IOException {
        final int SECRET_KEY = 5;
        /*
         文件加密
            为了保证文件的安全性，就需要对原始文件进行加密存储，在使用的时候再对其进行解密处理。

            加密原理:
            对原始文件中的每一个字节数据进行更改，然后将更改以后的数据存储到新的文件中。
            解密原理:
            读取加密之后的文件，按照加密的规则反向操作，变成原始文件。
         */

        // 1.创建对象关联原始文件
        FileInputStream fis = new FileInputStream("D:\\itheima\\movie.mp4");

        // 2.创建对象关联加密文件
        FileOutputStream fos = new FileOutputStream("D:\\itheima\\movie2.mp4");

        // 3.加密处理
        int b;
        while((b = fis.read()) != -1) {
            // 一次 异或是加密 第二次异或就是解密 但是异或的对象要一样
            fos.write(b ^ SECRET_KEY);
        }

        // 4.释放资源
        fis.close();
        fos.close();
    }
}
