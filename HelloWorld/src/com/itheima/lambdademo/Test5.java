package com.itheima.lambdademo;

import java.util.Arrays;

public class Test5 {
    /*
        定义数组并存储一些女朋友对象，利用Arrays中的sort方法进行排序
        要求1:属性有姓名、年龄、身高。
        要求2:按照年龄的大小进行排序，年龄一样，按照身高排序，身高一样按照姓名的字母进行排序。
        (姓名中不要有中文或特殊字符，会涉及到后面的知识)
     */
    public static void main(String[] args) {
        Student[] students = new Student[]{
                new Student("小李", 17, 1.60),
                new Student("小王", 18, 1.70),
                new Student("小张", 19, 1.80),
        };

        Arrays.sort(students, (s1, s2) -> {
            double result = s1.getAge() - s2.getAge();
            result = result == 0 ? s1.getHeight() - s2.getHeight() : result;
            result = result == 0 ? s1.getName().compareTo(s2.getName()) : result;

           if (result > 0)
               return 1;
           else if (result < 0)
               return -1;
           return 0;
        });
        System.out.println(Arrays.toString(students));

    }
}
