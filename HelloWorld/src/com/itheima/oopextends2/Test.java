package com.itheima.oopextends2;

public class Test  {
    static void main() {
      /*
        现在有三个电子设备，请设计他们的继承结构
        安卓手机
            属性：品牌 价格
            行为：打电话 发短信 NFC功能

        苹果手机
            属性：品牌 价格
            行为：打电话 发短信

        平板电脑
            属性：品牌 价格
            行为：编程
       */

        // 创建安卓手机对象
        AndroidPhone androidPhone = new AndroidPhone();
        androidPhone.brand = "华为";
        androidPhone.price = 2999.99;
        androidPhone.call();
        androidPhone.sendMessage();
        androidPhone.nfc();

        System.out.println("-----------------------------");

        // 创建苹果手机对象
        IosPhone iphone = new IosPhone();
        iphone.brand = "苹果";
        iphone.price = 9999.99;
        iphone.call();
        iphone.sendMessage();

        System.out.println("-----------------------------");
        // 创建平板电脑对象
        Laptop laptop = new Laptop();
        laptop.brand = "联想";
        laptop.price = 4999.99;
        laptop.programming();
    }
}
