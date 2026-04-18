package com.itheima.collectionDemo;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class SetDemo4 {
    public static void main(String[] args) {
        /*
        LinkedHashSet集合特点：有序、不重复、无索引
          这里的有序指的是保证存储和取出的元素顺序一致
           因为虽然底层数据结构依然是哈希表，但是每个元素又额外的多了一个双链表
           的机制，记录存储的顺序。

        在以后如果要数据去重，我们使用哪个？
            默认使用HashSet
            如果要求去重且存取有序，才使用LinkedHashSet
         */

        Student s1 = new Student("张三", 18);
        Student s2 = new Student("李四", 18);
        Student s3 = new Student("王五", 32);
        Student s4 = new Student("赵六", 18);
        Student s5 = new Student("张三", 18);

        LinkedHashSet<Student> s = new LinkedHashSet<>();
        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);
        s.add(s5); // false

        // 打印的时候会按照添加的顺序进行打印
        for (Student student : s) {
            System.out.println( student.getName() + "," + student.getAge());
        }

    }
}
