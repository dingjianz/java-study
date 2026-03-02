package com.itheima.oopextends7;

public class Son extends Father {
    private int c = 0x444;
    int d = 0x555;
    static int staticSon = 0x666;

    public Son() {
    }

    public Son(int a, int b, int c, int d) {
        super(a, b); // 调用父类的构造方法
        this.c = c;
        this.d = d;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
}
