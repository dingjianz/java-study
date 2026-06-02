package com.itheima.ioDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*
        什么是解压缩？
            将压缩文件还原成原始的文件和文件夹
            是压缩的逆过程
            恢复文件的原始大小和内容


        为什么需要解压缩？
           下载的文件：网上下载的软件、资料常是压缩格式
           节省空间：长期存储的文件可以压缩保存
           批量传输：多个文件压缩成一个方便传输
           备份数据：压缩备份节省存储空间
         */

        // 1.创建一个File表示要解压的压缩包
        File src = new File("HelloWorld/src/com/itheima/ioDemo/zipdir.zip");

        // 创建一个 File表示解压的目的地

        File dest = new File("HelloWorld/src/com/itheima/ioDemo/zipdirCopy");

        unZip(src, dest);

    }

    // 定义一个方法用来解压
    public static void unZip(File src, File dest) throws IOException {
        // 解压的本质：把压缩包里面的每一个文件或者文件夹读取出来，按照层级拷贝到目的地当中去

        // 创建一个ZipInputStream对象，构造方法中传递输入流
        ZipInputStream zis = new ZipInputStream(new FileInputStream(src));

        // 要先获取到压缩包里面的每一个 ZipEntry 对象
        ZipEntry entry;
        while((entry = zis.getNextEntry()) != null) {
           if (entry.isDirectory()) {
            // 文件夹：需要在目的地 dest 处创建一个同样的文件夹
               File file = new File(dest, entry.toString());
               file.mkdirs();
           } else {
               // 文件：需要读取到压缩包中的文件，并把它存放到目的地dest文件夹中（按照层级目录进行存放）
               File file = new File(dest, entry.toString());
               file.getParentFile().mkdirs();
               FileOutputStream fos = new FileOutputStream(file);

               int b;
               while((b = zis.read()) != -1) {
                   // 写到目的地
                   fos.write(b);
               }
               fos.close();
               // 表示在压缩包中的一个文件处理完毕了
               zis.closeEntry();
           }
        }

        // 释放资源
        zis.close();
    }
}
