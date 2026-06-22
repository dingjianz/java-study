package com.itheima.ioDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringJoiner;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class Test4 {
    public static void main(String[] args) throws IOException {
        /*
            文本文件中有以下的数据:2-1-9-4-7-8
            将文件中的数据进行排序，变成以下的数据:1-2-4-7-8-9
         */

        FileInputStream fis = new FileInputStream("src/main/java/com/itheima/ioDemo/1.txt");
        StringBuilder sb = new StringBuilder();

        int len;
        while ((len = fis.read()) != -1) {
            sb.append((char) len);
        }

        Integer[] arr = Arrays.stream(sb.toString().split("-"))
                .map(Integer::parseInt)
                .sorted()
                .toArray(Integer[]::new);

        StringJoiner sj = new StringJoiner("-");
        for (Integer i : arr) {
            sj.add(i.toString());
        }

        FileOutputStream fos = new FileOutputStream("src/main/java/com/itheima/ioDemo/2.txt");
        fos.write(sj.toString().getBytes());

        fos.close();
    }
}
