package com.itheima.collectionDemo;

public class SetDemo2 {
    public static void main(String[] args) {
        /*

        HashSet集合的底层原理：
        1.HashSet集合底层结构是一个哈希表
        2.哈希表： 是一种对于增删改查数据性能都好的数据结构

        哈希表组成：数组+链表+红黑树
         JDK8之前： 数组+链表
         JDK8之后： 数组+链表+红黑树

        哈希值：
            根据 hashCode方法算出来的int类型的整数
            该方法定义在Object类中，所有对象都可以调用，默认使用地址值进行计算
            一般情况下，会重写hashCode方法，利用对象内部的属性值计算哈希值

            对象的哈希值特点
             1.如果没有重写hashCode方法，不同对象计算出的哈希值是不同的
             2.如果已经重写hashCode方法，不同对象只要属性值相同，计算出的哈希值是相同的
             3.在小部分情况下，不同的属性值或者不同的地址值计算出来的哈希值也有可能一样（哈希碰撞）
             毕竟int范围是 -21亿 到 21亿
         */

        Student s1 = new Student("小王", 18);
        Student s2 = new Student("小王", 18);
        System.out.println(s1.hashCode()); // 1247233941
        System.out.println(s2.hashCode()); // 258952499
        // 重写hashCode之后 输出结果一样 23565815

        // String 类重写hashCode方法 虽然属性值不同，但是计算出的哈希值是相同的 小概率
        // 哈希碰撞现象
        System.out.println("abc".hashCode()); // 96354
        System.out.println("acD".hashCode()); // 96354


    }
}
