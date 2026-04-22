package com.itheima.mapDemo;

import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
    /*
        TreeMap集合特点：
        TreeMap跟TreeSet底层原理一样，都是红黑树结构的,增删改查性能较好
        由键决定特性:不重复、无索引、可排序
        可排序:对键进行排序。



        注意:默认按照键的从小到大进行排序，也可以自己规定键的排序规则

        代码书写两种排序规则
        实现Comparable接口，指定比较规则。
        创建集合时传递Comparator比较器对象，指定比较规则。
     */

        /*
        需求1:
            键:整数表示id
            值:字符串表示商品名称
            要求:按照id的升序排列、按照id的降序排列

         */

        TreeMap<Integer, String> tm = new TreeMap<>((o1, o2) -> o2 - o1);
        tm.put(1, "可口可乐");
        tm.put(2, "百事可乐");
        tm.put(3, "麦当劳");
        tm.put(4, "Dell显示器");
        tm.put(5, "MacBook Pro");

        // Integer、Double 默认升序排列
        // 字符、字符串按照在ASSI码的升序排列
        System.out.println(tm); // {5=MacBook Pro, 4=Dell显示器, 3=麦当劳, 2=百事可乐, 1=可口可乐}


        /*
        需求2:
            键:学生对象
            值:籍贯
            要求:按照学生年龄的升序排列，年龄一样按照姓名的字母排列，同姓名年龄视为同一个人。
         */

        Student s1 = new Student("小王", 18);
        Student s2 = new Student("小张", 24);
        Student s3 = new Student("小李", 60);
        Student s4 = new Student("小丁", 32);

        TreeMap<Student, String> tm2 = new TreeMap<>();
        tm2.put(s1, "北京");
        tm2.put(s2, "上海");
        tm2.put(s3, "广州");
        tm2.put(s4, "深圳");

        System.out.println(tm2);

    }
}
