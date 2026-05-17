package com.itheima.ioDemo;

import java.io.FileReader;
import java.io.IOException;

public class CharStreamDemo2 {
    public static void main(String[] args) throws IOException {
        // 1.创建字符流输入流对象
        FileReader fis = new FileReader("src/com/itheima/ioDemo/1.txt");

        // 2.读取数据
         /*
         read(chars): 读取数据、解码、强转三步合并了，把强转之后的字符放到数组中
                       相当于 空参的read() + 强转类型转换
          */

        char[] chs = new char[2];
        int len;
        while ((len = fis.read(chs)) != -1) {
            System.out.println(new String(chs, 0, len));
        }

        // 3.释放资源
        fis.close();

    }
}
