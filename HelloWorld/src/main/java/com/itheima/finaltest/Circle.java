package com.itheima.finaltest;

public class Circle {
    /*
    圆的半径
    Π的值
    圆的周长和面积
     */

    private double radius;
    private final double PI = 3.14; // 圆周率，常量

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public  double getPI() {
        return PI;
    }

    public double getPerimeter() {
        return 2 * PI * radius;
    }

    public double getArea() {
        return PI * radius * radius;
    }
}
