package com.itheima.oopextends3;

public class Test {
    static void main() {
        /*
            super关键字的使用：
                1. 在子类的成员方法中，访问父类的成员变量：super.父类成员变量名
                2. 在子类的成员方法中，访问父类的成员方法：super.父类成员方法名(参数列表)
                3. 在子类的构造方法中，访问父类的构造方法：super(参数列表)
                4. 方法重写时，如果只是在父类的同个方法基础上再添加一些逻辑
                     可以直接调用父类的同个方法来复用父类的代码，避免重复代码。
                     eg：在子类的show方法中调用父类的show方法来复用父类的代码。
                     public void show() {
                         super.show(); // 调用父类的show方法来复用父类的代码
                         // 添加子类特有的逻辑
                     }
         */
        Son son = new Son();
        son.show();

    }
}

