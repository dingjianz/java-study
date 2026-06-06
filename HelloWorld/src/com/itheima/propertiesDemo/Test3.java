package com.itheima.propertiesDemo;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Test3 {
    public static void main(String[] args) throws IOException {
        /*
        properties 和 IO 流结合的操作 之读取数据
         */

        Properties prop = new Properties();

        //2.读取本地 Properties 文件的数据到集合中
        FileReader fileReader = new FileReader("src/com/itheima/propertiesDemo/db.properties");
        prop.load(fileReader);
        fileReader.close();

        // 遍历集合
        System.out.println(prop);
    }
}
