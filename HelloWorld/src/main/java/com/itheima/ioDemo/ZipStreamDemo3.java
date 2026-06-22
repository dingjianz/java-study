package com.itheima.ioDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipStreamDemo3 {
    public static void main(String[] args) throws IOException {
        /*

             需求：把HelloWorld/src/main/java/com/itheima/ioDemo 文件夹进行压缩
         */

        // 1.创建一个File表示要压缩的文件夹
        File src = new File("HelloWorld/src/main/java/com/itheima/ioDemo");

        // 2.创建一个 File表示 压缩包的位置(压缩包的父级路径)
        File desParent = src.getParentFile(); // HelloWorld/src/main/java/com/itheima

        // 3.创建File对象表示压缩包的路径
        File dest = new File(desParent, src.getName() + ".zip");

        // 4.创建压缩流关联压缩包
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest));

        // 5. 获取src里面的没哦一个文件，变成ZipEntry对象，写入到压缩包中
        toZip(src, zos, src.getName());

        // 6.释放资源
        zos.close();
    }


    /**
     * 获取src里面的每一个文件，变成ZipEntry对象，写入到压缩包中
     * @param src 数据源
     * @param zos 压缩流
     * @param name 压缩包内部的路径
     * @throws IOException
     */
    public static void toZip(File src, ZipOutputStream zos, String name) throws IOException {
        // 1.进入src文件夹
        File[] files = src.listFiles();
        // 2.遍历files
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                // 3.判斷-文件，变成ZipEntry对象
                // 创建ZipEntry对象，表示压缩包里面的每一个文件和文件夹
                ZipEntry entry = new ZipEntry(name + "\\" + file.getName());
                zos.putNextEntry(entry);

                // 读取文件中的数据，写到压缩包
                FileInputStream fis = new FileInputStream(file);
                byte[] flush = new byte[1024];
                int len;
                while ((len = fis.read(flush)) != -1) {
                    zos.write(flush, 0, len);
                }
                fis.close();
                zos.closeEntry();
            } else {
                // 4.判斷-文件夹，递归
                toZip(file, zos, name + "\\" + file.getName());
            }
        }
    }
}
