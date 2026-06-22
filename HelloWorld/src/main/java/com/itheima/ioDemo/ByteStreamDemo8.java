package com.itheima.ioDemo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ByteStreamDemo8 {
    public static void main(String[] args) throws IOException {
     /*
        Java中编码的方法
          String类中的方法
            public byte[] getBytes() 使用默认方式进行编码 默认编码方式：UTF-8
            public byte[] getBytes(String charsetName) 使用指定的方式进行编码

        Java中解码的方法
         String类中的方法
            String(byte[] bytes) 使用默认方式进行解码
            String(byte[] bytes, String charsetName) 使用指定的方式进行解码
      */

        // 1.编码
        String s = "hello你好";
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));

        byte[] bytes1 = s.getBytes("GBK");
        System.out.println(Arrays.toString(bytes1));

        // 2.解码
        String s1 = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s1);

        String s2 = new String(bytes1, "GBK");
        System.out.println(s2);
    }
}
