package com.itheima.oop3;

public class Dog {
    /*
        private关键字：是一个权限修饰符，可以修饰成员变量、方法和构造方法，表示这些成员只能在本类中访问，外部无法直接访问。
        private修饰的成员只能在本类中访问，外部无法直接访问
        但是我们可以提供一些公共的方法来访问和修改这些成员变量，这些方法被称为getter和setter方法

        针对private修饰的成员变量，需提供以下操作
            提供setXxx(参数)，给成员变量赋值，用public修饰
            提供getXxx()，获取成员变量的值，用public修饰

        private修饰成员变量的好处：
        1. 数据封装：通过private修饰成员变量，可以将数据封装在类内部，外部无法直接访问，这样可以保护数据的安全性，防止外部代码直接修改数据，导致数据不一致或错误。
        2. 控制访问权限：通过提供公共的getter和setter方法，可以控制对成员变量的访问权限，可以在setter方法中添加一些逻辑来验证输入的合法性，确保数据的正确性。

     */
    private String name;
    private int age;

    public void setName(String value) {
        name = value;
    }

    public void setAge(int value) {
        if (value < 0 || value > 20) {
            System.out.println("年龄不合法");
            return;
        }
        age = value;
    }

    public int getAge() {
        return age;
    }

    public void eatBone() {
        System.out.println("小狗" + name + "吃骨头，它今年" + age + "岁了");
    }
}
