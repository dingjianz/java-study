package com.itheima.ooppolymorphic3;

public class Car extends Tool {
    public Car() {
    }

    public Car(String brand, int speed) {
        super(brand, speed);
    }

    @Override
    public void move() {
        System.out.println(getBrand() + "汽车正在以" + getSpeed() + "km/h的速度行驶...");
    }

    public void honk() {
        System.out.println("汽车正在鸣笛...");
    }

}
