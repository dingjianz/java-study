package com.itheima.ooppolymorphic3;

public class Test {
    static void main() {
        /*
        多态的综合练习

        自行车：
            属性： 品牌 行驶速度
            行为： 移动move 响铃ringBell

        汽车：
            属性： 品牌 行驶速度
            行为： 移动move 响铃honk

        人：
            属性： 姓名 年龄、性别
            行为： 行驶交通工具（能使用所有交通工具）

          测试类中创建Person的对象，让这个人使用任意一款交通工具
         */

        Person person = new Person("张三", 20, "男");

        Bike bike = new Bike("捷安特", 20);
        Car car = new Car("宝马", 120);

        person.useTool(bike);
        person.useTool(car);
    }
}
