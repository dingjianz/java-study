package com.itheima.fileDemo;

import java.io.File;

public class Test7 {
    public static void main(String[] args) {
        /*
          File 类相关的 API 的练习题
          需求：统计一个文件夹中的总大小
         */
        long totalSize = getTotalSize(new File("D://"));
        System.out.println(totalSize);
    }

    public static long getTotalSize(File src) {
        File[] files = src.listFiles();
        long totalSize = 0;
        if (files == null) {
          return 0;
        }

        for (File f : files) {
            if (f.isFile()) {
                totalSize += f.length();
            } else {
                totalSize += getTotalSize(f);
            }
        }
        return totalSize;
    }

}
