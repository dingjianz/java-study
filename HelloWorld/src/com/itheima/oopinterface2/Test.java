package com.itheima.oopinterface2;

public class Test {
    static void main() {
        /*
         jdk7 之后接口的新特性
            1. default 方法（默认方法）
            - 可以在接口中提供方法的默认实现
            - 使用 default 关键字修饰
            - 实现类可以选择重写或直接使用默认实现
            - 如果实现了多个接口，多个接口中存在相同名字的默认方法，子类就必须对该方法进行重写

          jdk8 之后接口中新增的方法
            1. 允许在接口中定义静态方法，需要用static修饰
            2. 接口中静态方法的定义格式
                格式： public static 返回值类型 方法名(参数列表) {}
                范例： public static void show() {}


            jdk9 之后接口中新增的方法
             1.私有方法
             - 接口中可以定义私有方法（普通私有方法 和 静态私有方法）
             - 作用：抽取多个 default/static 方法中的公共代码，避免重复，不对外暴露
             - 格式：private 返回值类型 方法名(参数列表) {}
             - 范例：private void log(String msg) {}

             - 格式：private static 返回值类型 方法名(参数列表) {}
             - 范例：private static void log(String msg) {}
         */

        InterImpl interImpl = new InterImpl();
        interImpl.method1();
        interImpl.method2();
        interImpl.method3();

        interImpl.login();
    }
}
