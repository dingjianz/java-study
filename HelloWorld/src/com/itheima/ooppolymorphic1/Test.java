package com.itheima.ooppolymorphic1;

public class Test {
    static void main() {
        /*
            对象的三大特征：
                1.封装
                2.继承
                3.多态

                多态：同一个对象在不同的时间表现出不同的状态

                 多态的表现形式：
                 父类类型 对象名称 = new 子类类型();

                 多态的前提：
                    1.必须有继承关系/实现关系
                    2.有父类引用指向子类对象
                    3.有方法重写

                 多态的好处：
                    1.提高了代码的扩展性
                    2.提高了代码的维护性
         */

        Student student = new Student("张三", "zhangsan", "123");
        Teacher teacher = new Teacher("李四", "lisi", "456");
        Admin admin = new Admin("王五", "wangwu", "789");

        StudentManager studentManager = new StudentManager();
        studentManager.register(student);
        studentManager.register(teacher);
        studentManager.register(admin);
    }
}
