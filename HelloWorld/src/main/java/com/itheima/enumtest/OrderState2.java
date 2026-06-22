package com.itheima.enumtest;

public enum OrderState2 {
   // 生成两个参数的枚举类
    WAIT_PAY("待支付", 1),
    WAIT_DELIVER("待发货", 2),
    WAIT_RECEIVE("待收货", 3),
    FINISH("已完成", 4);

    private final String name;
    private final int code;

    OrderState2(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
}
