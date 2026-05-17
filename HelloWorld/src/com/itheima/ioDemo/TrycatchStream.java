package com.itheima.ioDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TrycatchStream {
    public static void main(String[] args) throws IOException {
        // JDK7 以前
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //1.创建输入流对象
            fis = new FileInputStream("D:\\itheima\\moview.mp4");
            //2.创建输出流对象
            fos = new FileOutputStream("1.mp4");
            byte[] bytes = new byte[1024 * 1024 * 5];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 3.释放资源
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // JDK7 新特性
        /*
        try(创建流对象) {} catch (IOException e) {}
        try 后面的小括号中写创建对象的代码
        注意：只有实现了 AutoCloseable 接口的对象，才能放在 try()小括号中
         */
        try (FileInputStream fis2 = new FileInputStream("D:\\itheima\\moview.mp4");
             FileOutputStream fos2 = new FileOutputStream("1.mp4")) {
            byte[] bytes = new byte[1024 * 1024 * 5];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // JDK9 新特性
        FileInputStream fis3 = new FileInputStream("D:\\itheima\\moview.mp4");
        FileOutputStream fos3 = new FileOutputStream("1.mp4");
        try (fis3; fos3) {
            byte[] bytes = new byte[1024 * 1024 * 5];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
