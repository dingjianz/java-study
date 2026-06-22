package com.itheima.variable;

public class ArrayDemo {
    public static void main(String[] args) {
        Good[] goods = new Good[3];
        goods[0] = new Good("1", "电脑", 5000, 1);
        goods[1] = new Good("2", "鼠标", 20, 2);
        goods[2] = new Good("3", "键盘", 10, 3);

        for (int i = 0; i < goods.length; i++) {
            Good good = goods[i];
            System.out.println(good.getId() + " " + good.getName() + " " + good.getPrice() + " " + good.getCount());
        }
    }
}
