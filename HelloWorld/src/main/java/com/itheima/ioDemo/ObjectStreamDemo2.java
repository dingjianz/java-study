package com.itheima.ioDemo;

import com.itheima.mapDemo.Student;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectStreamDemo2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
           反序列化流/对象操作输入流：可以把序列化到本地文件中的对象，读取到程序中来

           构造方法:
               public ObjectInputStream(InputStream in) 把基本流包装成高级流

           成员方法：
               public Object readObject() 把序列化到本地文件的对象，读取到程序中来

         */

        // 1.创建反序列化流的对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/com/itheima/ioDemo/1.txt"));

        Student o = (Student) ois.readObject();

        System.out.println(o.getName());

        ois.close();

    }
}
