package com.itheima.ioDemo;

import java.io.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        /*
           拷贝一个文件夹，考虑子文件夹
         */

        // 1.创建对象表示数据源
        File src = new File("D:\\itheima\\movie");

        // 2.创建对象表示目的地
        File dest = new File("D:\\itheima\\movie2");

        // 3.调用拷贝方法
        copyDir(src, dest);
    }

    public static void copyDir(File src, File dest) throws IOException {
        dest.mkdirs();
        File[] files = src.listFiles();
        if(files != null) {
            for (File file : files) {
                if(file.isFile()) {
                    // 3.判断文件，拷贝
                    FileInputStream fis = new FileInputStream(file); // eg: 1.txt
                    FileOutputStream fos = new FileOutputStream(new File(dest, file.getName()));
                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = fis.read(bytes)) != -1) {
                        fos.write(bytes, 0, len);
                    }
                    fis.close();
                    fos.close();
                } else {
                    // 4.判断文件夹，递归
                    File destDir = new File(dest, file.getName());
                    copyDir(file, destDir);
                }
            }
        }
    }
}
