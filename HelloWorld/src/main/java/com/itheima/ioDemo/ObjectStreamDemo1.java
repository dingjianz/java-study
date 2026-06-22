package com.itheima.ioDemo;


import com.itheima.mapDemo.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*
           序列化流/对象操作输出流：可以把Java中的对象写到本地文件中。

           构造方法:
               public ObjectOutputStream(OutputStream out) 把基本流包装成高级流

           成员方法：
               public final void writeObject(Object obj) 把对象序列化（写出）到文件中

            序列化
         */

        // 1.创建对象 需要 implements Serializable
        Student stu = new Student("张三", 18);

        // 2.创建序列化流的对象/对象操作输出流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/java/com/itheima/ioDemo/1.txt"));

        oos.writeObject(stu);

        oos.close();

    }
}
