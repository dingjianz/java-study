package com.itheima.ioDemo;

import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo2 {
    public static void main(String[] args) throws IOException {
        /*
            FileOutputStream
                换行
                    \r\n  windows
                    \n  Linux
                    \r  mac

                续写 FileOutputStream
                第二个参数  true 表示续写
                false 表示覆盖

         */

        // 1. 创建字节输出流对象
        FileOutputStream fos = new FileOutputStream("src/main/java/com/itheima/ioDemo/1.txt", true);

        // 2. 写数据
        String str = "hello world\r\nhello java\r\nhello itheima";
        fos.write(str.getBytes());

        // 3. 释放资源
        fos.close();


    }
}
