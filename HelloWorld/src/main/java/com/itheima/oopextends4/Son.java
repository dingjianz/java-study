package com.itheima.oopextends4;

class Son extends Father {
    String grade; // 年级

    public Son() {
        // super(); // 调用父类的无参构造方法，默认会调用父类的无参构造方法，如果父类没有无参构造方法，则必须显式调用父类的有参构造方法。
        System.out.println("儿子的无参构造方法被调用了");
    }

    public Son(String name, int age, String grade) {
        super(name, age); // 调用父类的有参构造方法，必须在子类构造方法的第一行调用父类的构造方法。
        this.grade = grade;
        System.out.println("儿子的有参构造方法被调用了");
    }

}
