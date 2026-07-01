package com.itheima.UserList.service.impl;

import com.itheima.UserList.dao.impl.UserDaoImpl;
import com.itheima.UserList.pojo.User;
import com.itheima.UserList.dao.UserDao;
import com.itheima.UserList.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

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
