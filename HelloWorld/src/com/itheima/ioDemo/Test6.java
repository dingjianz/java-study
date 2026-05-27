package com.itheima.ioDemo;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Test6 {
    public static void main(String[] args) throws IOException {
        /*
            需求：把《出师表》的文章顺序进行恢复到一个新文件中。
         */

        // 1.读取数据
        BufferedReader br = new BufferedReader(new FileReader("D:\\chushibiao.txt"));
        ArrayList<String> list = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();

        // 2.排序
        // 排序规则：按照每一行前面的序号进行排列
        list.sort((o1, o2) -> {
            int i1 = Integer.parseInt((o1.split("\\.")[0]));
            int i2 = Integer.parseInt((o2.split("\\.")[0]));
            return i1 - i2;
        });

        // 3.写入数据
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\copy.txt"));
        for (String s : list) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();
    }
}
