package com.itheima.ioDemo;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConvertStreamDemo2 {
    public static void main(String[] args) throws IOException {
        /*
            利用转换流按照指定字符编码写出
         */

        // 1.创建转换流对象
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("d:\\demo\\a.txt"), Charset.forName("GBK"));

        // 2.写出数据
        osw.write("你好");

        // 3.关闭流
        osw.close();

        // jdk 11 开始，可以直接使用 FileWriter
        FileWriter fw = new FileWriter("d:\\demo\\a.txt", Charset.forName("GBK"));
        fw.write("你好");
        fw.close();
    }

}
