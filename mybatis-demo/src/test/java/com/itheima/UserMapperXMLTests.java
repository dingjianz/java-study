package com.itheima;

import com.itheima.mapper.UserMapperXML;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperXMLTests {
    @Autowired
    private UserMapperXML userMapperXML;

    @Test
    void testFindAll() {
        List<User> users = userMapperXML.findAll();
        users.forEach(System.out::println);
    }
}
