package com.itheima.lambdademo;

public class Test2 {
    /*
        1.利用匿名内部类的形式去调用下面的方法
        调用一个方法的时候，如果方法的形参是一个接口，那么我们要传递这个接口的实现类对象
        如果实现类对象只要用到一次，就可以用匿名内部类的形式进行书写
     */
    public static void main(String[] args) {
        method(new Swimmer() {
            @Override
              public void swim() {
                System.out.println("游泳");
            }
        });

        method(() -> System.out.println("lambda游泳"));
    }

    static public void method(Swimmer sw) {
        sw.swim();
    }

    @FunctionalInterface
    interface Swimmer {
        public abstract void swim();
    }

}
