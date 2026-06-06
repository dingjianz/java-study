package com.itheima.propertiesDemo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args) throws IOException {
        /*
        properties 和 IO 流结合的操作 之存数据
         */

        Properties prop = new Properties();

        //2.添加数据
        prop.setProperty("username", "zhangsan");
        prop.setProperty("age", "18");

        // 3.把集合中的数据写到文件中
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/com/itheima/propertiesDemo/db.properties"));
        prop.store(bw, "save data");
        bw.close();
    }
}
