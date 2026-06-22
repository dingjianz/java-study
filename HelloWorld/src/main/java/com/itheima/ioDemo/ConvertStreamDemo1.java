package com.itheima.ioDemo;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConvertStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*
            利用转换流按照指定字符编码读取
         */

        // 1.创建转换流对象
        InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\a.txt"), StandardCharsets.UTF_8);
        int ch;
        while ((ch = isr.read()) != -1) {
            System.out.print((char) ch);
        }
        isr.close();

        // jdk 11 开始，可以直接使用 FileReader
        FileReader fr = new FileReader("D:\\a.txt", Charset.forName("GBK"));
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }
        fr.close();

    }

}
