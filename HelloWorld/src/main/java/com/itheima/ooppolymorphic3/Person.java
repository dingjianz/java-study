package com.itheima.ooppolymorphic3;

public class Person {
    private String name; // 姓名
    private int age; // 年龄
    private String gender; // 性别

    public Person() {
    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void useTool(Tool tool) {
        System.out.println(name + "正在使用" + tool.getBrand() + "交通工具...");
        tool.move();
        if (tool instanceof Bike bike) { // Java 16引入的模式匹配，简化了向下转型的代码
           // Bike bike = (Bike) tool; // 向下转型
            bike.ringBell(); // 这里调用了Tool类中没有的方法，编译时会报错
        } else if (tool instanceof Car) {
            Car car = (Car) tool; // 向下转型
            car.honk(); // 这里调用了Tool类中没有的方法，编译时会报错
        }
    }
}
