package com.itheima.UserList.controller;

import com.itheima.UserList.pojo.User;
import com.itheima.UserList.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    /*
    controller:控制层，接收前端发送的请求，对请求进行处理，并相应数据。
    service：业务逻辑层，处理具体的业务逻辑，
    dao: 数据访问层(Data AccessObject)(持久层),负责数据访问操作，包括数据的增删改查。

    控制反转：Inversion of Control,简称IOC。对象的创建控制权转移到外部(容器)，这种思想称为控制反转。
    依赖注入：Dependency Injection,简称DI。容器为应用程序提供运行时，所以来的资源，称之为依赖注入。
    Bean对象：IOC容器中创建、管理的对象，称之为Bean。

    1.实现分层解耦的思路是什么？
        将项目中的类交给IOC容器管理（IOC，控制反转）
        应用程序运行时需要什么对象，直接依赖容器为其提供(DI，依赖注入)

        1.将Dao 以及 Service 层的实现类，交给IOC容器管理
        2.为Controller 及 Service 注入运行时所依赖的对象


        IOC详解
            要把某个对象交给IOC容器管理，需要在对应的类上加上如下注解之一：
            @Component 声明bean的基础注解，不属于以下三类时，用此注解
            @Controller @Component的衍生注解 标注在控制层上
            @Service @Component的衍生注解 标注在业务层上
            @Repository @Component的衍生注解 标注在数据访问层类上(由于与mybatis整合，用的少)

            声明bean的时候，可以通过注解的value属性指定bean的名字，如果没有置顶，默认为类名首字母小写。
            eg: @Component("xxYyyy")

            前面声明bean的四大注解，要想生效，还需要被组件扫描注解 @COmponentScan 扫描
            该注解虽然没有显示配置，但是实际上已经包含在了启动类声明注解 @SpringBootApplication 中，
            默认的扫描的范围是启动类所在包及其子包。

         DI详解：基于 @Autowired 进行依赖注入的常见方式有如下三种：
            1.属性注入
            2.构造函数注入
            3.setter注入

     */
    // @Autowired
    @Resource // 应用程序运行时，会自动的查询该类型的bean对象，并赋值给改成员变量
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list() throws Exception {
        return userService.findAll();
    }
}
