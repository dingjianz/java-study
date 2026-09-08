package com.itheima.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class JedisConnectionFactory {
    private static final JedisPool jedisPool;

    static {
        JedisPoolConfig jedispoolConfig = new JedisPoolConfig();
        // 连接池最大连接数：同一时刻最多能借出 10 个 Jedis 连接，达到上限后新的获取请求需要排队等待
        jedispoolConfig.setMaxTotal(10);
        // 最大空闲连接数：池中最多保留 10 个空闲连接，超出的会被销毁。设成和 maxTotal 一样可避免频繁创建/销毁连接
        jedispoolConfig.setMaxIdle(10);
        // 最小空闲连接数：0 表示不预热、不常驻，空闲连接可以全部回收；设为 >0 可减少突发流量下的建连延迟
        jedispoolConfig.setMinIdle(0);
        // 获取连接的最大等待时间：池满时最多阻塞多久，超时抛异常。负数/极大值表示几乎无限等待
        jedispoolConfig.setMaxWait(Duration.ofMillis(100));

        // 参数依次为：池配置、Redis 主机、端口、连接超时（毫秒）、密码
        jedisPool = new JedisPool(jedispoolConfig, "172.31.101.75", 6380, 1000, "1234");
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
