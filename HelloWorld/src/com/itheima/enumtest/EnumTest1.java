package com.itheima.enumtest;

public class EnumTest1 {
    public static void main() {
        OrderState O1 = OrderState.WAIT_PAY;
        System.out.println("订单状态是：" + O1.getName());

    }
}
