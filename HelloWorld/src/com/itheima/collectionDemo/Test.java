package com.itheima.collectionDemo;

import java.util.ArrayList;
import java.util.Collection;

public class Test {
    public static void main(String[] args) {
      /*
        单列集合Collection 和双列集合 Map
            Collection 是单列集合的祖宗接口，它的功能是全部单列集合都可以继承使用的。

            1.单列集合Collection：
                 1.1 List: ArrayList LinkedList Vector
                 1.2 Set: HashSet TreeSet
                List系列集合：添加的元素是有序、可重复、有索引
                Set系列集合：添加的元素是无序、不重复、无索引


                public boolean add(E e) {} 把给定的对象添加到当前集合中
                public void clear() {} 清空集合中的元素
                public boolean remove(Object o) {} 删除集合中指定的元素
                public boolean contains(Object o) {} 判断当前集合中是否包含给定的元素
                public boolean isEmpty() {} 判断当前集合是否为空
                public int size() {} 获取当前集合的元素个数


       */

        /*
            注意点： Collection 是一个接口，我们不能直接创建它的对象
            集合的实现类有：ArrayList LinkedList Vector HashSet TreeSet
         */

        // 目的：为了学习Collection接口中的方法
        // 自己在做一些练习的时候，还是按照之前的方式去创建对象
        Collection<String> coll = new ArrayList<>();

        // 1.添加元素
        /*
            如果往List 系列集合中添加数据，那么方法永远返回true
            因为List系列集合中允许添加重复的元素
            如果往Set系列集合中添加数据，那么添加的元素必须满足两个条件：
            1.元素不能重复
            2.元素必须唯一
            如果添加的元素重复，那么添加失败，返回false
         */
        coll.add("hello");
        coll.add("world");


        /*
            2.删除元素
            如果删除的元素不存在，那么删除失败，返回false
            因为Collection中定义的是共性的方法
            所以此时不能用过索引去进行删除
            只能通过元素的对象进行删除
            如果删除的元素是多个，那么删除的是第一个匹配的元素
         */
        coll.remove("world");

        /*
            3.判断元素是否存在
            底层是依赖equals()方法判断是否存在
            如果集合中存储的对象是自定义对象，也想通过 contains()方法判断元素是否存在，
            那么自定义对象必须重写equals()方法

            eg: 下面的Student 类
         */
        boolean b = coll.contains("hello");
        System.out.println(b); // true
        System.out.println(coll);

        /*
            4.集合的长度
         */
        int size = coll.size();
        System.out.println(size); // 1

        /*
            5.判断集合是否为空
         */
        boolean empty = coll.isEmpty();
        System.out.println(empty); // false
        coll.clear();
        System.out.println(coll.isEmpty()); // true

        System.out.println("-----------------");

        // 1. 创建集合的对象 用来演示 自定义对象的contains()方法要重写 equals()方法
        Collection<Student> students = new ArrayList<>();
        Student stu1 = new Student("张三", 18);
        Student stu2 = new Student("李四", 22);
        Student stu3 = new Student("王五", 16);

        // 2. 添加元素
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);

        // 3. 判断集合中某一个学生对象是否包含
        Student stu4 = new Student("张三", 18);

        /*
            如果同名同姓同龄，就认为是同一个学生
            如果直接使用equals()方法判断，那么返回false，默认会使用Object类的equals()方法判断
            而Object类的equals()方法判断两个对象是否相同，就是判断两个对象的地址是否相同
            所以需要重写equals()方法，在对应的Javabe中，Student类中重写equals()方法
         */
        System.out.println(students.contains(stu4)); // true
    }
}
