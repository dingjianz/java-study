package com.itheima.ooppolymorphic1;

public class StudentManager {

    public void register(Person person) {
        person.work();
        System.out.println(person.getName() + "注册成功,用户名是:" + person.getUsername() + ",密码是:" + person.getPassword());
    }
}
