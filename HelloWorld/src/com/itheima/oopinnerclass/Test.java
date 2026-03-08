package com.itheima.oopinnerclass;

// Outer 和 Test 在同一包下，无需 import
public class Test {
    static void main() {
        /*
            一、成员内部类：
            - 定义在成员位置（类中方法外）
            - 可以直接访问外部类的所有成员，包括私有
            - 外部类访问内部类需要创建对象

            什么时候用到内部类？
                ⏺ 当一个类只为某个特定类服务，不需要对外暴露时用内部类。
                  最典型的场景：

                  // 例如：汽车类，发动机只属于汽车，外部不需要单独使用它
                  public class Car {
                      class Engine {
                          void start() { ... }
                      }
                  }

            创建内部类对象的格式：
                外部类名.内部类名 对象名 = new 外部类名().new 内部类名();
                范例：Outer.Inner oi = new Outer().new Inner();

               当成员内部类被 private 修饰，则外部类要加一个方法，getInnerStance


             二、静态内部类
                1. 静态内部类也是成员内部类的一种
                2. 静态内部类只能访问外部类中的静态变量和静态方法
                   如果想要访问非静态的需要创建对象。

                   创建静态内部类对象的格式
                      外部类名.内部类名 对象名 = new 外部类名.内部类名();

                    调用静态方法的格式
                        外部类名.内部类名.方法名();

              三、局部内部类
                1.将局部类定义在方法里面就叫做局部内部类，类似于方法里面的局部变量。
                2.外界是无法直接使用，需要在方法内部创建对象并使用。
                3.该类可以直接访问外部类的成员，也可以访问方法内的局部变量。

               四、匿名内部类
               1.就是一个没有名字的内部类，可以写在成员位置，也可以写在局部位置；
               2.作用：在继承一个类或者实现一个接口的时候少写一个文件，也是lambda的前置知识点。
               3.匿名内部类的格式：
                    new 类名/接口名() {
                        重写方法
                    }
                4.匿名内部类等价于
                    没有名字的java类 + 实现接口/继承类 + 重写方法 + 创建对象；
                5。使用场景
                    如果实现类只需要使用一次，就可以用匿名内部类简化代码
         */

        // 创建成员内部类对象
//        Outer.Inner oi = new Outer("张三").new Inner();
//        oi.show();
        Outer.Inner oi = new Outer("dc").new Inner();
        oi.show();

        Outer2.Inner oi2 = new Outer2.Inner();
        System.out.println(oi2.name);

        Outer3 o3 = new Outer3();
        o3.show();

    }
}
