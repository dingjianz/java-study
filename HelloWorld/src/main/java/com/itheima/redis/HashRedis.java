package com.itheima.redis;

/**
 * Hash类型

  Hash类型，也叫散列，其value是一个无序字典，类似于Java中的HashMap结构。

  String结构是将对象序列化为JSON字符串后存储，当需要修改对象某个字段时很不方便：

  ┌─────────────────┬──────────────────────────────┐
  │       KEY       │            VALUE             │
  ├─────────────────┼──────────────────────────────┤
  │  heima:user:1   │  {name:"Jack", age:21}       │
  │  heima:user:2   │  {name:"Rose", age:18}       │
  └─────────────────┴──────────────────────────────┘

  Hash结构可以将对象中的每个字段独立存储，可以针对单个字段做CRUD：

  ┌─────────────────┬──────────────────────────────┐
  │                 │            VALUE             │
  │       KEY       ├───────────────┬──────────────┤
  │                 │     field     │    value     │
  ├─────────────────┼───────────────┼──────────────┤
  │                 │     name      │    Jack      │
  │  heima:user:1   ├───────────────┼──────────────┤
  │                 │     age       │    21        │
  ├─────────────────┼───────────────┼──────────────┤
  │                 │     name      │    Rose      │
  │  heima:user:2   ├───────────────┼──────────────┤
  │                 │     age       │    18        │
  └─────────────────┴───────────────┴──────────────┘

  ===== Hash类型的常见命令 =====

  Hash的常见命令有：

  • HSET key field value：添加或者修改hash类型key的field的值
      规则：HSET key field value [field value ...]
            key不存在时自动创建；field已存在则覆盖旧值
      返回：新增的field个数（覆盖不计数）
      示例：HSET heima:user:1 name Jack     → 1
            HSET heima:user:1 age 21        → 1
            HSET heima:user:1 name Tom      → 0（name已存在，只覆盖）

  • HGET key field：获取一个hash类型key的field的值
      规则：HGET key field
            key或field不存在都返回 nil
      示例：HGET heima:user:1 name          → "Jack"
            HGET heima:user:1 phone         → (nil)

  • HMSET：批量添加多个hash类型key的field的值
      规则：HMSET key field value [field value ...]
            Redis 4.0 起已被 HSET 的多参数写法取代，功能等价
      返回：OK
      示例：HMSET heima:user:2 name Rose age 18   → OK

  • HMGET：批量获取多个hash类型key的field的值
      规则：HMGET key field [field ...]
            按参数顺序返回，不存在的field对应位置为 nil
      示例：HMGET heima:user:2 name age phone
            → 1) "Rose"  2) "18"  3) (nil)

  • HGETALL：获取一个hash类型的key中的所有的field和value
      规则：HGETALL key
            返回field与value交替排列的列表；field多时慎用（会阻塞）
      示例：HGETALL heima:user:1
            → 1) "name"  2) "Jack"  3) "age"  4) "21"

  • HKEYS：获取一个hash类型的key中的所有的field
      规则：HKEYS key
            key不存在时返回空列表（不报错）
      示例：HKEYS heima:user:1
            → 1) "name"  2) "age"

  • HVALS：获取一个hash类型的key中的所有的value
      规则：HVALS key
            返回顺序与 HKEYS 一一对应
      示例：HVALS heima:user:1
            → 1) "Jack"  2) "21"

  • HINCRBY：让一个hash类型key的字段值自增并指定步长
      规则：HINCRBY key field increment
            increment为整数，可为负数实现自减；field不存在时按0起算
            field的value必须是整型字符串，否则报错
      返回：自增后的值
      示例：HINCRBY heima:user:1 age 2       → 23
            HINCRBY heima:user:1 age -1      → 22
            浮点自增用 HINCRBYFLOAT key field 0.5

  • HSETNX：添加一个hash类型的key的field值，前提是这个field不存在，否则不执行
      规则：HSETNX key field value
            只能一次设置一个field
      返回：1 表示设置成功，0 表示field已存在、未执行
      示例：HSETNX heima:user:1 phone 13800138000   → 1
            HSETNX heima:user:1 phone 13900139000   → 0（已存在，不覆盖）

 */
public class HashRedis {

}
