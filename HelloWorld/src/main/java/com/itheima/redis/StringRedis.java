package com.itheima.redis;

/**
 * String类型的常见命令

  String的常见命令有：

  • SET：添加或者修改已经存在的一个String类型的键值对

  • GET：根据key获取String类型的value

  • MSET：批量添加多个String类型的键值对

  • MGET：根据多个key获取多个String类型的value

  • INCR：让一个整型的key自增1

  • INCRBY：让一个整型的key自增并指定步长，例如：incrby num 2 让num值自增2

  • INCRBYFLOAT：让一个浮点类型的数字自增并指定步长

  • DECR：让一个整型的key自减1

  • DECRBY：让一个整型的key自减并指定步长

  • SETNX：添加一个String类型的键值对，前提是这个key不存在，否则不执行

  • SETEX：添加一个String类型的键值对，并且指定有效期
        SETEX key seconds value

  ===== SET 命令的扩展用法 =====

  1. SET 配合 NX 选项（等同于 SETNX）
     SET key value NX
     示例：SET lock:order:123 1 NX
     说明：仅当 key 不存在时才设置，常用于分布式锁

  2. SET 配合 EX 选项（等同于 SETEX）
     SET key value EX seconds
     示例：SET code:login:13800138000 "123456" EX 300
     说明：设置键值对并指定过期时间（秒），常用于验证码、session等

  3. SET 配合 NX 和 EX 组合使用
     SET key value NX EX seconds
     示例：SET lock:user:999 1 NX EX 30
     说明：仅当 key 不存在时设置，并指定过期时间，常用于带过期时间的分布式锁



 */
public class StringRedis {

}
