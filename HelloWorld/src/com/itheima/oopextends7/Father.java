package com.itheima.oopextends7;

public class Father {
    private int a = 0x111;
    int b = 0x222;
    static int staticFather = 0x333;

    public Father() {
    }

    public Father(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
