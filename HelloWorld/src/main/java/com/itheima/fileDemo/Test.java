package com.itheima.fileDemo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        /*
         File类的使用
           File 对象表示一个路径，可以是文件的路径，也可以是文件夹的路径
           这个路径可以存在的，也可以是不存在的。

           public File(String pathname) 根据文件路径创建文件对象
           public File(String parent, String child) 根据父路径和子路径创建文件对象
           public File(File parent, String child) 根据父文件对象和子路径创建文件对象

           File的常见成员方法（判断、获取）
                public boolean exists() 判断此路径表示的文件或目录是否存在
                public boolean isFile() 判断此路径是否是一个文件
                public boolean isDirectory() 判断此路径是否是一个文件夹
                public String getAbsolutePath() 获取此路径的绝对路径
                public String getPath() 获取此路径的相对路径
                public String getName() 获取此路径的文件或文件夹的名称（带后缀）
                public long length() 获取此路径表示的文件的长度(字节数量)
                public long lastModified() 获取此路径表示的文件最后修改时间
         */

        String path = "/Users/lijiaqi/Downloads/1.txt";
        File f1 = new File(path);
        System.out.println(f1); // /Users/lijiaqi/Downloads/1.txt


        File f2 = new File("/Users/lijiaqi/Downloads", "1.txt");
        System.out.println(f2); // /Users/lijiaqi/Downloads/1.txt


        File parentFile = new File("/Users/lijiaqi/Downloads");
        File f3 = new File(parentFile, "1.txt");
        System.out.println(f3); // /Users/lijiaqi/Downloads/1.txt

        System.out.println("-------------------------------");

        // 1.判断是不是文件夹
        File f4 = new File("/Users/lijiaqi/Downloads");
        System.out.println(f4.isDirectory()); // true

        // 2.判断是不是文件
        File f5 = new File("/Users/lijiaqi/Downloads/1.txt");
        System.out.println(f5.isFile()); // true

        // 3.对一个不存在的路径进行判断
        File f6 = new File("/Users/lijiaqi/Downloads/2.txt");
        System.out.println(f6.exists()); // false

        // 4.获取此路径表示的文件的长度
        System.out.println(f5.length()); // 1134

        // 5.获取此路径的绝对路径
        File f7 = new File("HelloWorld\\example.txt");
        System.out.println(f7.getAbsolutePath()); // /Users/lijiaqi/Documents/Java学习/my-project/HelloWorld/HelloWorld\example.txt

        // 6.获取此路径的相对路径
        System.out.println(f7.getPath()); // HelloWorld\example.txt

        // 7.获取此路径的文件或文件夹的名称
        System.out.println(f5.getName()); // 1.txt

        // 8.获取此路径的父路径
        System.out.println(f5.getParent()); // /Users/lijiaqi/Downloads

        // 9.获取此路径最后修改时间
        System.out.println(f5.lastModified()); // 1777443139825
        // 时间戳转 时间
        Date d = new Date(f5.lastModified());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(d)); // 2026-04-29 14:12:19
    }
}
