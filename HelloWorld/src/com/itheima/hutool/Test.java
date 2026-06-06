package com.itheima.hutool;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        /*
        FileUtil 类：
            file：根据参数创建一个 file 对象
            touch：根据参数创建一个文件

            writeLines：把集合中的数据写出到文件中，覆盖模式
            appendLines：把集合中的数据写出到文件中，追加模式
            readLines：指定字符编码，把文件中的数据读取到集合中
            readUtf8Lines：指定文件，把文件中的数据读取到集合中，使用 UTF-8 编码

            copy：拷贝文件或者文件夹
         */

        /*File file = FileUtil.file("D:\\", "aaa", "bbb", "1.txt");
        System.out.println(file);*/

        /*File file1 = new File("D:\\aaa\\bbb\\1.txt");
        file1.createNewFile(); // java自带的写法 父级文件不存在 会直接报错*/

        /*File touch = FileUtil.touch(file); // 假如 aaa bbb 父级文件夹不存在 也会创建
        System.out.println(touch);*/

        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        File file = FileUtil.writeLines(list, "HelloWorld/src/com/itheima/hutool/1.txt", StandardCharsets.UTF_8, false);
        System.out.println(file);

        // readLines 默认一行是一个数据
        List<String> strings = FileUtil.readLines(file, Charset.defaultCharset());
        System.out.println(strings);

    }
}
