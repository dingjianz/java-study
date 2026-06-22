package com.itheima.ioDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo7 {
    public static void main(String[] args) throws IOException {
     /*
        练习：文件拷贝
         把 D:\itheima\moview.mp4 文件拷贝到 当前模块下
      */

        //1.创建输入流对象
        FileInputStream fis = new FileInputStream("D:\\itheima\\moview.mp4");
        //2.创建输出流对象
        FileOutputStream fos = new FileOutputStream("1.mp4");
        byte[] bytes = new byte[1024 * 1024 * 5];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }

        // 3.释放资源
        fis.close();
        fos.close();
    }
}
