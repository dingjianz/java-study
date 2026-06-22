package com.itheima.ooppolymorphic3;

public class Bike  extends Tool {
    public Bike() {
    }

    public Bike(String brand, int speed) {
        super(brand, speed);
    }

    @Override
    public void move() {
        System.out.println(getBrand() + "自行车正在以" + getSpeed() + "km/h的速度行驶...");
    }

    public void ringBell() {
        System.out.println("自行车正在响铃...");
    }
}
