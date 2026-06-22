package com.itheima.fileDemo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class Test4 {
    public static void main(String[] args) {
        /*
          File的常见成员方法（获取并遍历）
             public static File[] listRoots()  // 获取系统中的所有盘符
             public String[] list()  // 获取当前目录下的所有文件/文件夹（仅仅获取到文件/文件夹的名字）
             public String[] list(FilenameFilter filter) // 利用文件名过滤器获取当前目录下的所有文件/文件夹
             public File[] listFiles() // 获取当前目录下的所有文件/文件夹
             public File[] listFiles(FileFilter filter)
             public File[] listFiles(FilenameFilter filter)

         */

        // listRoots 获取系统中的所有盘符
        File[] files = File.listRoots();
        System.out.println(files.length); // 1
        System.out.println(Arrays.toString(files)); // [/]

        // list 获取当前目录下的所有文件/文件夹 （仅仅获取到文件/文件夹的名字）
        File file = new File("/Users/lijiaqi/Downloads");
        String[] names = file.list();
        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("===================");

        //  list(FilenameFilter filter) 利用文件名过滤器获取当前目录下的所有文件/文件夹
        File file1 = new File("/Users/lijiaqi/Downloads");
        //  String[] names1 = file1.list(new FilenameFilter() {
        //      @Override
        //      public boolean accept(File dir, String name) {
        //          return name.endsWith(".png");
        //      }
        //  });

        String[] names1 = file1.list((dir, name) -> name.endsWith(".png"));

        System.out.println(Arrays.toString(names1));
    }
}
