package com.itheima.oopinnerclass;

// Outer 和 Test 在同一包下，无需 import
public class Test {
    static void main() {
        /*
            成员内部类：
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
         */

        // 创建成员内部类对象
        Outer.Inner oi = new Outer().new Inner();
        oi.show();
    }
}
