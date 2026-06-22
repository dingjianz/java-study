package com.itheima.oopinterface2;

public class InterImpl implements Inter {
    @Override
    public void method1() {
        System.out.println("实现类重写method1方法～");
    }

    @Override
    public void method2() {
        System.out.println("实现类重写method2方法～");
    }

//    @Override
//    public void method3() {
//        System.out.println("重写default method3");
//    }
}
