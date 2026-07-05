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

    @Test
    void testDeleteById() {
        userMapper.deleteById(1);
    }

    @Test
    void testInsertUser() {
        User user = User.builder()
                .age(29)
                .name("周瑜")
                .username("zhouyu")
                .password("123456")
                .build();
        userMapper.inertUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = User.builder()
                .id(16)
                .age(29)
                .name("周瑜2")
                .username("zhouyu2")
                .password("123456")
                .build();
        userMapper.updateUser(user);
    }

    @Test
    public void testFindByUsernameAndPassword() {
        User user = userMapper.findByUsernameAndPassword("zhouyu", "123456");
        System.out.println("返回结果：" + user);
    }
}
