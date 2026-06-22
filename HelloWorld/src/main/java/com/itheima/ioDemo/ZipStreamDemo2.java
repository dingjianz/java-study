package com.itheima.ioDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipStreamDemo2 {
    public static void main(String[] args) throws IOException {
        /*
            什么是压缩？
             压缩本质：把每一个（文件/文件夹）堪称ZipEntry对象放到压缩包中

             需求：把HelloWorld/src/main/java/com/itheima/ioDemo/1.txt进行压缩
         */

        // 1.创建一个File表示要压缩的文件
        File src = new File("HelloWorld/src/main/java/com/itheima/ioDemo/1.txt");

        // 创建一个 File表示 压缩包的位置
        File dest = new File("HelloWorld/src/main/java/com/itheima/ioDemo");

        toZip(src, dest);

    }

    // 定义一个方法用来压缩
    public static void toZip(File src, File dest) throws IOException {
        // 1.创建压缩流关联压缩包
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(dest, "1.zip")));

        // 2.创建 ZipEntry对象，表示压缩包里面的每一个文件和文件夹
        ZipEntry entry = new ZipEntry(src.getName());

        // 3.把ZipEntry对象写入到压缩包中
        zos.putNextEntry(entry);

        // 4.把要压缩的文件写入到压缩包中
        FileInputStream fis = new FileInputStream(src);
        byte[] buf = new byte[1024];
        int len;
        while((len = fis.read(buf)) != -1) {
            zos.write(buf, 0, len);
        }
        zos.closeEntry();

        fis.close();
        zos.close();


    }
}
