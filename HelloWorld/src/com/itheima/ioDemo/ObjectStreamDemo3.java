package com.itheima.ioDemo;

import java.io.*;
import java.util.ArrayList;

public class ObjectStreamDemo3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    /*
        将多个自定义对象序列化到文件中，但是对象的个数不确定，该如何操作呢？
     */

        // 1.序列化多个对象
        Student stu1 = new Student("张三", 20, "南京");
        Student stu2 = new Student("李四", 21, "上海");
        Student stu3 = new Student("王五", 22, "北京");
        System.out.println(stu1);

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);


        // 创建序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/com/itheima/ioDemo/1.txt"));
        oos.writeObject(students);

        // 创建反序列化流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/com/itheima/ioDemo/1.txt"));

        ArrayList<Student> lists = (ArrayList<Student>) ois.readObject();

        for (Student it : lists) {
            System.out.println(it);
        }

        // Object obj4 = ois.readObject(); // EOFException 报错

        ois.close();
    }
}
