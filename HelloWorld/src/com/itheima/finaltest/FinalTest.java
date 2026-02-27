package com.itheima.finaltest;

import com.itheima.oop5.Student;

public class FinalTest {
    public static void main(String[] args) {
      /*
        final修饰成员变量
        特点：叫做常量，值不能改变。必须初始化，且只能初始化一次。
         访问：直接访问。
         常量名大写，多个单词用下划线连接。

         细节：
            基本数据类型 byte short int long float double char boolean
            1. final修饰基本数据类型，值不能改变。
            2. final修饰引用数据类型，引用不能改变，但对象的内容可以改变。
       */
        final int[] NUMBER_ARR = {1, 2, 3, 4, 5};

        final Student STU = new Student("张三", 20);
        STU.setName("李四"); // 引用不能改变，但对象的内容可以改变。
        STU.setAge(22); // 如果成员变量不想改变，需要加final修饰成员变量。
        System.out.println("STU的名字是：" + STU.getName()); // 李四
        System.out.println("STU的年龄是：" + STU.getAge()); // 22
    }
}