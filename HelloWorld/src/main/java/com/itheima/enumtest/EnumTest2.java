package com.itheima.enumtest;

public class EnumTest2 {
    public static void main(String[] args) {
        // 获取枚举常量
        OrderState2 state = OrderState2.WAIT_PAY;
        System.out.println(state.getName()); // 待支付
        System.out.println(state.getCode()); // 1

        // 遍历所有枚举常量
//        for (OrderState2 s : OrderState2.values()) {
//            System.out.println(s.name() + " -> " + s.getName() + ", code: " + s.getCode());
//        }

        OrderState2[] codes = OrderState2.values();
        for (OrderState2 s : codes) {
            System.out.println(s.name() + " -> " + s.getName() + ", code: " + s.getCode());

        }
        System.out.println(OrderState2.valueOf("WAIT_DELIVER")); // 获取枚举常量对象
    }
}
