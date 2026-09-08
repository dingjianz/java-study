package com.itheima;

import com.itheima.pojo.LoginInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisDemoApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        // 写入一条String数据
        redisTemplate.opsForValue().set("name", "heima");
        // 读取一条String数据
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void testObject() {
        // 写入一条Object数据
        redisTemplate.opsForValue().set("heima:user:5", new LoginInfo(1, "heima", "jianding9", "123456"));
        // 读取一条Object数据
        LoginInfo user = (LoginInfo) redisTemplate.opsForValue().get("heima:user:5");
        System.out.println(user);
    }
}
