package com.itheima.ioDemo;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConvertStreamDemo3 {
    public static void main(String[] args) throws IOException {
        /*
            将本地文件中GBK格式的数据 转换为 UTF-8格式文件
         */

        // jdl11 之前的方法
        InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\a.txt"), "GBK");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\b.txt"), "UTF-8");
        int len;
        while((len = isr.read()) != -1) {
            osw.write(len);
        }
        isr.close();
        osw.close();

        // jdk11 新的方法
        FileReader fr = new FileReader("D:\\a.txt", Charset.forName("GBK"));
        FileWriter fw = new FileWriter("D:\\b.txt", StandardCharsets.UTF_8);
        int len2;
        while((len2 = fr.read()) != -1) {
            fw.write(len2);
        }
        fr.close();
        fw.close();
    }

}
