package com.itheima.enumtest;

public enum OrderState {
    // 枚举类：是一种特殊的类，表示一组常量。每个常量都是枚举类的一个实例。
    // 枚举类的定义：使用enum关键字定义枚举类，枚举类的名字通常以大写字母开头。
    // 枚举类的成员：枚举类的成员是枚举常量，枚举常量是枚举类的实例。枚举常量之间用逗号分隔，最后一个枚举常量后面可以加分号。
    // 在枚举类的第一行，把所有的对象创建好，放在第一行，后面可以添加成员变量、成员方法、构造方法等。

    /*
      枚举常量本质上是对象实例

          当你写：
          WAIT_PAY("待支付"),
          WAIT_DELIVER("待发货"),
          WAIT_RECEIVE("待收货"),
          FINISH("已完成");

          这些其实是在创建对象，等价于：

          public static final OrderState WAIT_PAY = new OrderState("待支付");
          public static final OrderState WAIT_DELIVER = new OrderState("待发货");
          public static final OrderState WAIT_RECEIVE = new OrderState("待收货");
          public static final OrderState FINISH = new OrderState("已完成");

     */

    // 订单状态：待支付、待发货、待收货、已完成
    WAIT_PAY("待支付"),
    WAIT_DELIVER("待发货"),
    WAIT_RECEIVE("待收货"),
    FINISH("已完成");

    // 所有枚举项默认都有一个隐式的public static final修饰的实例对象，枚举类的构造方法默认是private修饰的，不能被外部调用。
    // eg： public static final OrderState WAIT_PAY = new OrderState("待支付");


    private final String name;

    private OrderState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
