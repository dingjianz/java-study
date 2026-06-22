package com.itheima.ioDemo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CommonsIoDemo {
    public static void main(String[] args) throws IOException {
     /*
     Commons-io是一个开源的java工具类库，提供了很多与IO流相关的工具类。
        显著提高 IO 流的开发效率。

        1.在项目中创建一个文件夹：lib
        2.将 jar 包复制粘贴到 lib 文件夹
        3.右键点击 jar 包，选择 Add as Library -> 点击OK
        4.在类中导包使用

        FileUtils类
           static void copyFile(File srcFile, File destFile)     复制文件
           static void copyDirectory(File srcDir, File destDir)   直接复制目录
           static void copyDirectoryToDirectory(File srcDir, File destDir)  先创建文件夹再复制文件夹到之前创建的文件夹中
           static void deleteDirectory(File directory)  删除文件夹，整个删除掉
           static void cleanDelete(File file)   清空文件夹，保留文件夹
           static void readFileToString(File file, Charset encoding)   读取文件中的数据转换成字符
           static void write(File file, CharSequence data, String encoding)   将数据写入文件中

        IOUtils类
          public static int copy(InputStream input, OutputStream output) 复制文件
          public static void copyLarge(Reader input, Writer output) 复制大文件
          public static String readLines(Reader input) 读取数据
          public static void write(String data, OutputStream output) 写出数据
      */

        // ... existing code ...

       /*
        File srcFile = new File("HelloWorld/src/main/java/com/itheima/ioDemo/1.txt");
        File destFile = new File("HelloWorld/src/main/java/com/itheima/ioDemo/1_copy.txt");
        FileUtils.copyFile(srcFile, destFile);
        */

        /*
        File srcFile = new File("HelloWorld/src/main/java/com/itheima/ioDemo");
        File destFile = new File("HelloWorld/src/main/java/com/itheima/ioDemoCopy");
        FileUtils.copyDirectory(srcFile, destFile);
         */

        /*
        File srcFile = new File("HelloWorld/src/main/java/com/itheima/ioDemo");
        File destFile = new File("HelloWorld/src/main/java/com/itheima/ioDemoCopy");
        FileUtils.copyDirectoryToDirectory(srcFile, destFile);
         */
    }
}


