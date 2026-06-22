package com.itheima.oop2;

public class Ooptest2 {

    public static void main() {
        /*
            描述一类事物的类叫做JavaBean类，JavaBean类的特点：
                1.成员变量私有化
                2.提供公共的getter/setter方法
                3.提供无参构造方法

             带有main方法的类叫做测试类，测试类的特点：
                1.成员变量可以是任意访问权限
                2.提供main方法

              Javabean类和测试类的区别：
                1.JavaBean类是用来描述一类事物的，测试类是用来测试JavaBean类的
                2.JavaBean类的成员变量私有化，测试类的成员变量可以是任意访问权限
                3. JavaBean类可以写属性和行为，测试类一般只写main方法
         */
        Student student1 = new Student();
        student1.name = "张三";
        student1.age = -20;
        student1.gender = '男';
        student1.height = 1.75;
        System.out.println(student1.age);


        Teacher teacher1 = new Teacher();
        teacher1.name = "李四";
        teacher1.age = 30;
        System.out.println(teacher1.name);
        teacher1.teach();
        teacher1.eat();
    }

}
