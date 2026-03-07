package com.itheima.oopinterface2;

public interface Inter {

    public abstract void method1();

    public abstract void method2();

    // public 可以省略 但是default不能省略
    public default void method3() {
        System.out.println("默认的method3 default方法");
    }

    public static void method4() {
        System.out.println("接口中的静态方法");
    }

    // JDK9 新增：私有方法
    // 作用：抽取多个 default 方法中的公共代码，避免重复，不对外暴露
    // 格式：private 返回值类型 方法名(参数列表) {}
    private void check() {
        System.out.println("检查网络");
        System.out.println("检查用户名和密码的格式");
        System.out.println("检查用户名是否存在");
    }


    public default void login() {
        check();
        System.out.println("执行登录的逻辑");
    }

    public default void register() {
        check();
        System.out.println("执行注册的逻辑");
    }
}
