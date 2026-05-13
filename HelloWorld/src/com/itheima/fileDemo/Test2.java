package com.itheima.fileDemo;

import java.io.File;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        /*
        File的常见成员方法（创建、删除）
          public boolean createNewFile() 创建新的空的文件
            当前的路径表示的文件是不存在的，返回true，表示创建成功
            当前的路径表示的文件是存在的，返回false，表示创建失败
            如果父级路径不存在，那么方法会报异常 IOException
            createNewFile创建的一定是文件，如果路径中不包含后缀名，则会默认创建一个没有后缀的普通文件

          public boolean mkdir() 创建文件夹
          public boolean mkdirs() 创建多级文件夹
          public boolean delete() 删除文件 或 文件夹
            - delete 方法默认只能删除文件和空文件夹，且直接删除 不走回收站
            - 如果删除的是空文件夹，则返回true
            - 如果删除的是一个非空文件夹，则返回false
         */

        // 1. createNewFile 创建一个新的空的文件
        File f1 = new File("example2.txt");
        boolean newFile = f1.createNewFile();
        System.out.println(newFile);

        // 2. mkdir 创建文件夹
        File f2 = new File("d:\\itheima");
        boolean mkdir = f2.mkdir();
        System.out.println(mkdir);

        // 3. mkdirs 创建多级文件夹
        File f3 = new File("d:\\itheima\\java\\itheima");
        boolean mkdirs = f3.mkdirs();
        System.out.println(mkdirs);

        // 4. delete 删除文件 或 文件夹

        boolean delete = f1.delete();
        System.out.println(delete); // true

    }
}
