package com.itheima.fileDemo;

import java.io.File;

public class Test6 {
    public static void main(String[] args) {
        /*
          需求：删除一个多级文件夹
            如果我们要删除一个有内容的文件夹
             1.先删除文件里面所有的内容
             2.再删除文件夹本身
         */
    }

    /**
     * 删除文件夹
     * @param dir
     */
    public static void deleteDir(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isFile()) {
                f.delete();
            } else {
                deleteDir(f);
            }
        }

        // 最后删除自己
        dir.delete();
    }
}
