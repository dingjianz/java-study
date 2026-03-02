package com.itheima.oopextends4;

public class Test {
    static void main() {
        /*
           继承中构造函数的访问特点是什么？
           1.子类不能继承父类的构造方法，但是可以通过super调用父类的构造方法来访问父类的构造方法。
           2.子类的构造方法默认会访问父类的无参构造方法，如果父类没有无参构造方法了，那么子类必须在构造方法中使用super关键字显式调用父类的有参构造方法。
           3. super(参数列表)必须放在子类构造方法的第一行。 新版本jdk不需要, 但是老版本jdk需要。建议遵守习惯。
         */

        Son son = new Son("小明", 18, "高三");

        System.out.println(son.name); // 访问父类的成员变量
        System.out.println(son.age); // 访问父类的成员变量
        System.out.println(son.grade); // 访问子类的成员变量
    }
}

