package com.itheima.oopextends2;

public class Phone extends SmartDevice {
    // 子类：手机特有的属性
    // 子类：手机特有的行为
    public void call() {
        System.out.println(brand + "正在打电话");
    }

    public void sendMessage() {
        System.out.println(brand + "正在发短信");
    }
}
