package com.itheima.collectionDemo;

import java.util.ArrayList;
import java.util.Iterator;

public class GenericsDemo {
    public static void main(String[] args) {
    /*
        泛型是 JDK5 新增的，可以在编译阶段约束操作的数据类型，并进行检查。
        泛型：<>中指定元素类型
        泛型在集合创建的时候指定，集合中只能支持引用数据类型，不能写基本数据类型
        如果不写泛型 默认泛型为Object

        泛型的好处：
          统一数据类型
          把运行时期的错误提前到编译期间进行检测
          避免了强制类型转换可能出现的问题

        泛型可以用在类class，接口Interface，方法，成员变量，局部变量，创建对象，数组。

        1. 泛性类使用场景：当一个类中，某个变量的类型不确定的时候，可以使用泛型类
        格式：修饰词 class 类名<E> {}  -> public class ArrayList<E> {}
        此处 E 表示元素类型 你可以理解为变量 但是不是用来记录数据的 而是记录数据的类型，可以写成： E、T、K、V等

        2. 泛型方法使用场景：当一个方法中，某个参数或者返回值类型不确定的时候，可以使用泛型方法
        格式：修饰词 <E> 返回值类型 方法名(E e) {}
        方案1：使用类名后面定义的泛型，本类中的所有方法都能使用
        方案2: 在方法申明定义自己的泛型，只有本方法能用

        3. 泛型接口使用场景：当一个接口中，某个方法或者属性的类型不确定的时候，可以使用泛型接口
        格式：修饰符 interface 接口名<E> {}
        方式1: 泛型接口的实现类给出具体的类型
        方式2: 实现类延续泛型，创建对象时再确定

     */
        // 1.不使用泛型，集合如何存储数据
        ArrayList list = new ArrayList();
        list.add("hello");
        list.add(12);
        Iterator it = list.iterator();
        // while (it.hasNext())  {
            // Object obj = it.next();
            // 多态的弊端 无法访问子类的特有功能
            // obj.length();

            // 强转类型会导致运行时异常
            // String s = (String) it.next();
            // System.out.println(s.length());
        //}
        System.out.println(list); // [hello, 12]
        System.out.println(list.get(0) instanceof String);


        System.out.println("-------------------");

        // 2.使用泛型，集合存储数据
        MyArrayList<String> list1 = new MyArrayList<>();
        ListUtil.addAll(list1, "hello", "world", "java", "javascript");
        System.out.println(list1);
    }
}
