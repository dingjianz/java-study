package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*
    SpringBoot单元测试的注解。
    当前测试类中的测试方法运行时，会启动springboot项目,IOC容器也会创建好
 */
@SpringBootTest
class MybatisDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testFindAll() {
        List<User> users = userMapper.findAll();
        users.forEach(System.out::println);
    }

}
