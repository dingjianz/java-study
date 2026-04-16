package com.itheima.collectionDemo;

import java.util.ArrayList;
import java.util.Collection;

public class GenericsDemo3 {
    public static void main(String[] args) {
    /*
        需求：定义一个方法，形参是一个集合，但是集合中的数据类型是不确定的。
     */

        ArrayList<Ye> list = new ArrayList<>();
        ArrayList<Fu> list1 = new ArrayList<>();
        ArrayList<Zi> list2 = new ArrayList<>();
        ArrayList<Student> list3 = new ArrayList<>();

        // 例如：只允许 添加 Ye或者 Fu或者 Zi 类型的元素
        // method(list); // 报错了 Ye 不是 Fu的子类Ï
        method(list1);
        method(list2);
       //  method(list3); // 报错 reason: ArrayList<Student> is not compatible with ArrayList<? extends Ye>
    }

    /*
      泛型通配符的用法：
        ?: 表示泛型通配符
        ? extends E : 表示泛型通配符，表示集合中元素类型未知，但是这个类型必须是E或者E的子类
        ? super E : 泛型通配符，表示集合中元素类型未知，但是这个类型必须是E或者E的父类

        应用场景：
        1. 如果我们在定义类、方法、接口的时候，如果类型不确定，就可以定义泛性类、泛型方法、泛型接口；
        2.如果类型不确定，但是能知道只能传递某个即成体系中的，就可以用泛型的通配符

        泛型通配符的关键用处： 可以先限定类型的范围
     */
    public static <E> void method(ArrayList< ? extends Fu> list) {

    }
}

class Student {
}

