package com.itheima;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.LoginInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * RedisTemplate的两种序列化实践方案：
 *
 * 方案一：
 * 1. 自定义RedisTemplate
 * 2. 修改RedisTemplate的序列化器为 GenericJackson2JsonRedisSerializer
 *
 * 方案二：
 * 1. 使用StringRedisTemplate
 * 2. 写入Redis时，手动把对象序列化为JSON
 * 3. 读取Redis时，手动把读取到的JSON反序列化为对象
 *
 * 本测试类演示的是【方案二】。
 */
@SpringBootTest
public class RedisStringTests {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() throws Exception {
        // 创建对象
        LoginInfo loginInfo = new LoginInfo(1, "heima", "jianding9", "123456");
        // 手动序列化
        String json = objectMapper.writeValueAsString(loginInfo);
        // 写入数据
        stringRedisTemplate.opsForValue().set("loginInfo:1", json);
        // 读取数据
        String jsonLoginInfo = stringRedisTemplate.opsForValue().get("loginInfo:1");
        // 手动反序列化
        LoginInfo loginInfo2 = objectMapper.readValue(jsonLoginInfo, LoginInfo.class);
        System.out.println(loginInfo2);
    }

    @Test
    void testHash() throws Exception {
        stringRedisTemplate.opsForHash().put("heima:user:6", "name", "宋江");
        stringRedisTemplate.opsForHash().put("heima:user:6", "age", "18");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("heima:user:6");
        System.out.println(entries);

        // stringRedisTemplate.opsForHash().delete("heima:user:6", "name");

        Map<String, Object> map = new HashMap<>();
        map.put("name", "柳泉");
        map.put("username", "liuquan");
        map.put("id", "10");
        map.put("token", "xxxx");

        stringRedisTemplate.opsForHash().putAll("heima:user:7", map);
    }


}
