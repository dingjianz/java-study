package com.itheima.variable;

public class VariableDemo1 {
    /*

        微信余额 0元
        支付宝余额 10元
        银行卡余额 20元

        问题一：请问现在一共有多少钱？
        问题二： 微信收了10元，又发了2元红包，余额多少？

     */

   public static void main() {
        // 记录微信余额
        double weChatBalance = 0.0;

        // 记录支付宝余额
        double alipayBalance = 10.0;

        // 记录银行卡余额
        double bankCardBalance = 20.0;

        // 计算总余额
        double totalBalance = weChatBalance + alipayBalance + bankCardBalance;
        System.out.println("现在一共有多少钱：" + totalBalance + "元");

        // 微信收了10元，又发了2元红包，计算新的微信余额
        weChatBalance = weChatBalance + 10.0 - 2.0;
        System.out.println("微信余额：" + weChatBalance + "元");
    }
}
