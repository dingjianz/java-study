package com.itheima.Objectdemo;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class clone {
    /*
        克隆对象
        方法在底层会帮我们创建一个对象，并把原对象中的数据拷贝过去。

        书写细节：
            1.重写Object中的clone方法
            2.让javabean类实现Cloneable接口
            3.调用clone方法
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Student s1 = new Student("张三", 18, arr);
        Student s2 = (Student) s1.clone();

       /*
          第三方工具 深拷贝
        */
        Gson gson = new Gson();
        Student s3 = gson.fromJson(gson.toJson(s1), Student.class);

        s1.setName("jianding9");
        s1.setAge(12);
        s1.getArrayList().add(666); // 会影响s2 不影响s3
        System.out.println(s1.getName() + "---" + s1.getAge() + "---" + s1.getArrayList());
        System.out.println(s2.getName() + "---" + s2.getAge() + "---" + s2.getArrayList());
        System.out.println(s3.getName() + "---" + s3.getAge() + "---" + s3.getArrayList());
        System.out.println(s1 == s2); // false
    }
}

