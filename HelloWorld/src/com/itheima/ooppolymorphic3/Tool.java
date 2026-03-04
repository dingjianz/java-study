package com.itheima.ooppolymorphic3;

public class Tool {
    private String brand; // 品牌
    private int speed; // 行驶速度

    public Tool() {
    }

    public Tool(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move() {
        System.out.println("交通工具正在移动...");
    }
}
