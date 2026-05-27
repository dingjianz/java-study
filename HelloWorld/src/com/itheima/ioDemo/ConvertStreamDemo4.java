package com.itheima.ioDemo;

import java.io.*;

public class ConvertStreamDemo4 {
    public static void main(String[] args) throws IOException {
        /*
            利用字节流读取文件中的数据，每次读一整行，且不能出现乱码

        前提条件：
            1.字节流在读取中文的时候，是会出现乱码的，因为是一个字节一个字节的读取
            2.字节流没有一次读取一整行的方法
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\a.txt")));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }
}
