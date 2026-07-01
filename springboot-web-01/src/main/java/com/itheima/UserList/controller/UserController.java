package com.itheima.UserList.controller;

import com.itheima.UserList.pojo.User;
import com.itheima.UserList.service.UserService;
import com.itheima.UserList.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    /*
    controller:控制层，接收前端发送的请求，对请求进行处理，并相应数据。
    service：业务逻辑层，处理具体的业务逻辑，
    dao: 数据访问层(Data AccessObject)(持久层),负责数据访问操作，包括数据的增删改查。

     */

    private final UserService userService = new UserServiceImpl();

    @RequestMapping("/list")
    public List<User> list(){
        return userService.findAll();
    }
}
