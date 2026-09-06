package com.itheima.redis;

public class Test {
    /*
        认识 Redis

        Redis 诞生于 2009 年，全称是 Remote Dictionary Server，远程词典服务器，
        是一个基于内存的键值型 NoSQL 数据库。

        特征：
        1. 键值（key-value）型，value 支持多种不同数据结构，功能丰富
        2. 单线程，每个命令具备原子性
        3. 低延迟，速度快（基于内存、IO 多路复用、良好的编码）
        4. 支持数据持久化
        5. 支持主从集群、分片集群
        6. 支持多语言客户端

        docker run -d \
          --name redis \
          -p 6379:6379 \
          -v /root/redis/data:/data \
          -v /root/redis/conf/redis.conf:/usr/local/etc/redis/redis.conf \
          --restart=always \
          redis:7.2 \
          redis-server /usr/local/etc/redis/redis.conf --appendonly yes --requirepass 你的密码

        redis-cli 所在目录
        cd /usr/local/bin/

        怎么使用命令行获取redis中的数据
        redis-cli -h 127.0.0.1 -p 6379 -a 你的密码
        redis-cli -a 你的密码
        get key
     */
}


