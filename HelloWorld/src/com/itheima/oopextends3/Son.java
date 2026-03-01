package com.itheima.oopextends3;

class Son extends Father {
    String name = "儿子";

    // 注解：Override表示重写父类的方法，编译器会检查是否正确重写了父类的方法，如果没有正确重写，会报错。
    // 重写：子类重新定义父类的方法，方法名、参数列表、返回值类型必须相同，访问修饰符不能更严格。
    // 了解 子类重写父类方法时，访问权限不能更严格，不能使用private修饰符，必须使用public或protected修饰符。
    // 了解 子类重写父类方法时，返回值类型必须相同或是父类方法返回值类型的子类。
    // 建议 子类重写父类方法时，方法名、参数列表、返回值类型都相同，这样更容易理解和维护。
    // final修饰类为最终类，不能被继承；final修饰方法为最终方法，不能被重写；final修饰变量为常量，必须初始化且不能修改。
    // private私有方法、static静态方法、final最终方法不能被重写，因为它们不能被子类访问或修改。
    @Override
    public void show() {
        String name = "儿子局部变量";
        System.out.println(name); // 输出儿子
        System.out.println(this.name); // 输出儿子
        System.out.println(super.name); // 输出父亲
    }
}
