package com.itheima.oopextends6;

public class Test {
    static void main(String[] args) {
        /*
        带有继承结构的标准JavaBean类

        书写一个完整的继承体系，要求私有化成员变量、get/set方法、构造方法、其他的成员方法

        本科学生：
        属性：姓名、年龄、年级
        行为：吃饭、睡觉、学习（攻读学士学位）

        专业课老师：
        属性：姓名、年龄、学科
        行为：吃饭、睡觉、教书（教专业课知识）

        硕士研究生：
        属性：姓名、年龄、年级
        行为：吃饭、睡觉、学习（攻读硕士学位）

        通识课老师：
        属性：姓名、年龄
        行为：吃饭、睡觉、教书（教通识课知识）

        过了一段时间，硕士研究生住宿条件升级，在豪华版学生公寓睡觉
         */

        Student1 stu1 = new Student1("张三", 20, "大三");
        stu1.study();
        System.out.println(stu1.getName());

        Student2 stu2 = new Student2("李四", 25, "研二");
        stu2.study();
        System.out.println(stu2.getName());

        Teacher teacher = new Teacher("王五", 40);
        teacher.teach();
        System.out.println(teacher.getName());

        Teacher1 teacher1 = new Teacher1("赵六", 35, "数学");
        teacher1.teach();
        System.out.println(teacher1.getName());
    }
}
