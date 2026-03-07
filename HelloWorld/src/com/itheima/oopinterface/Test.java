package com.itheima.oopinterface;

public class Test {
    public static void main(String[] args) {
        /*
            接口: 是一种特殊的抽象类，接口中的成员变量只能是常量，接口中的方法只能是抽象方法。接口不能被实例化，接口只能被实现。
                1. 接口中的成员变量默认是 public static final 的，可以省略不写。
                eg：int A = 10; // 等价于 public static final int A = 10;

                2. 接口中的方法默认是 public abstract 的，可以省略不写。
                eg：void eat(); // 等价于 public abstract void eat();

                3. 接口不能有构造方法，因为接口不能被实例化。
                4. 一个类可以实现多个接口，接口之间用逗号分隔。
                5. 实现接口的类必须实现接口中的所有抽象方法，否则这个类必须是抽象类。
                6. 接口可以继承多个接口，接口之间用逗号分隔。
                7. 接口不能继承类，接口只能继承接口。
                8. 接口中的成员变量和方法都是 public 的，接口中的成员变量和方法不能有其他访问修饰符。
                9. 接口中的成员变量和方法不能有 static 修饰符，接口中的成员变量和方法不能有 final 修饰符。
                10. 接口中的成员变量和方法不能有 private 修饰符，接口中的成员变量和方法不能有 protected 修饰符。
                11. 接口中的成员变量和方法不能有 default 修饰符，接口中的成员变量和方法不能有 synchronized 修饰符。
                12. 接口中的成员变量和方法不能有 native 修饰符，接口中的成员变量和方法不能有 strictfp 修饰符。
                13. 接口中的成员变量和方法不能有 transient 修饰符，接口中的成员变量和方法不能有 volatile 修饰符。
                14. 接口中的成员变量和方法不能有 synchronized 修饰符，接口中的成员变量和方法不能有 native 修饰符。
                15. 接口中的成员变量和方法不能有 strictfp 修饰符，接口中的成员变量和方法不能有 transient 修饰符。

              接口和接口的关系
                1. 接口之间可以继承，且可以多继承，接口之间用逗号分隔。
                2. 接口不能继承类，接口只能继承接口。
                3. 接口中的成员变量和方法都是 public 的，接口中的成员变量和方法不能有其他访问修饰符。


            练习:
                青蛙
                    属性: 颜色、年龄
                    行为: 吃虫子、蛙泳
                 狗
                    属性: 颜色、年龄
                    行为: 吃骨头、狗刨
                  兔子
                    属性: 颜色、年龄
                    行为: 吃胡萝卜
         */
        Dog dog = new Dog("旺财", "黄色");
        dog.eat();
        dog.swim();

        Frog frog = new Frog("呱呱", "绿色");
        frog.eat();
        frog.swim();

        Rabbit rabbit = new Rabbit("跳跳", "白色");
        rabbit.eat();

    }
}
