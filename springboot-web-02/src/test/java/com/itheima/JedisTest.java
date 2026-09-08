package com.itheima;

import com.itheima.utils.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JedisTest {

    private Jedis jedis;

    @BeforeEach
    public void setup() {
        // 建立连接
        jedis = JedisConnectionFactory.getJedis();

        // 设置密码
        jedis.auth("1234");

        // 选择库
        jedis.select(0);
    }

    @AfterEach
    public void tearDown() {
        // 释放连接
        if (jedis != null) {
            jedis.close();
        }
    }

    @Test
    public void testString() {
        // 存入数据
        String result = jedis.set("name", "虎哥");
        System.out.println("result = " + result);

        // 获取数据
        String name = jedis.get("name");
        System.out.println("name = " + name);

        assertEquals("虎哥", name);
    }

    @Test
    public void hashTest() {
        // 方式一：hmset 一次写入多个字段（key 唯一签名是 Map）
        Map<String, String> user = new HashMap<>();
        user.put("name", "Rose");
        user.put("age", String.valueOf(21));
        user.put("sex", "female");
        String result = jedis.hmset("heima:user:2", user);
        System.out.println("result = " + result);

        // 方式二：hset 一次写一个字段（多字段版 hset 需要 Redis 4.0+，这里用 hmset 更通用）
        jedis.hset("heima:user:3", "name", "Jack");
        jedis.hmset("heima:user:3", Map.of("age", "18", "sex", "male"));

        // 读取整个 hash
        Map<String, String> all = jedis.hgetAll("heima:user:2");
        System.out.println("all = " + all);

        // 按字段批量读取，返回值顺序与传入的字段一致
        List<String> values = jedis.hmget("heima:user:2", "name", "age");
        System.out.println("values = " + values);

        assertEquals("Rose", all.get("name"));
    }
}
