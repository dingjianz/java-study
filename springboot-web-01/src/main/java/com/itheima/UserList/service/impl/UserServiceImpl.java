package com.itheima.UserList.service.impl;

import com.itheima.UserList.pojo.User;
import com.itheima.UserList.dao.UserDao;
import com.itheima.UserList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// @Component // 将当前类交给IOC容器管理
@Service // 标注在业务层上，将当前类交给IOC容器管理
public class UserServiceImpl implements UserService {
    @Autowired // 应用程序运行时，会自动的查询该类型的bean对象，并赋值给改成员变量
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        List<String> all = userDao.findAll();
        return all.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return User.builder().id(id).username(username).password(password).name(name).age(age).updateTime(updateTime).build();
        }).toList();
    }
}
