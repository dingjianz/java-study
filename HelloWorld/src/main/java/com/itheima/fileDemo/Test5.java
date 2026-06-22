package com.itheima.fileDemo;

import java.io.File;

public class Test5 {
    public static void main(String[] args) {
        /*
            需求：找到电脑中所有以txt结尾的电影（需要考虑子文件夹）
         */
        findTxt(new File("D://"));
    }

    public static void findTxt(File src) {
        File[] files = src.listFiles();
        if (files == null) {
            // 没有权限的文件夹访问 listFiles() 会返回null
            return;
        }
        for (File f : files) {
            if (f.isFile()) {
                if (f.getName().endsWith(".txt")) {
                    System.out.println(f);
                }
            } else {
                findTxt(f);
            }
        }
    }
}
