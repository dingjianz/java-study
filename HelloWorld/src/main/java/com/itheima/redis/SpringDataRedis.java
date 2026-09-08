package com.itheima.redis;

public class SpringDataRedis {
    /*
    SpringDataRedis 快速入门

    SpringDataRedis 中提供了 RedisTemplate 工具类，其中封装了各种对 Redis 的操作。
    并且将不同数据类型的操作 API 封装到了不同的类型中：

    | API                         | 返回值类型        | 说明                    |
    | --------------------------- | --------------- | ---------------------- |
    | redisTemplate.opsForValue() | ValueOperations | 操作 String 类型数据      |
    | redisTemplate.opsForHash()  | HashOperations  | 操作 Hash 类型数据        |
    | redisTemplate.opsForList()  | ListOperations  | 操作 List 类型数据        |
    | redisTemplate.opsForSet()   | SetOperations   | 操作 Set 类型数据         |
    | redisTemplate.opsForZSet()  | ZSetOperations  | 操作 SortedSet 类型数据   |
    | redisTemplate               |                 | 通用的命令               |
     */
}
