package com.itheima.redis;

/**
 * Set类型

  Redis的Set结构与Java中的HashSet类似，可以看做是一个value为null的HashMap。
  因为也是一个hash表，因此具备与HashSet类似的特征：
  • 无序
  • 元素不可重复
  • 查找快
  • 支持交集、并集、差集等功能

  ===== Set类型的常见命令 =====

  Set的常见命令有：

  • SADD key member ...：向set中添加一个或多个元素
      规则：SADD key member [member ...]
            key不存在时自动创建；已存在的元素被忽略（不重复）
      返回：真正新增的元素个数
      示例：SADD s1 a b c        → 3
            SADD s1 a           → 0（a已存在）

  • SREM key member ...：移除set中的指定元素
      规则：SREM key member [member ...]
            不存在的元素直接忽略；元素全部移除后key自动删除
      返回：真正移除的元素个数
      示例：SREM s1 a b          → 2
            SREM s1 x           → 0（x不存在）

  • SCARD key：返回set中元素的个数
      规则：SCARD key
            key不存在时返回0；时间复杂度O(1)
      示例：SCARD s1             → 3

  • SISMEMBER key member：判断一个元素是否存在于set中
      规则：SISMEMBER key member
      返回：1 表示存在，0 表示不存在
      示例：SISMEMBER s1 a       → 1
            SISMEMBER s1 x       → 0

  • SMEMBERS：获取set中的所有元素
      规则：SMEMBERS key
            返回结果无序；元素多时慎用（会阻塞），可用 SSCAN 分批遍历
      示例：SMEMBERS s1          → 1) "c"  2) "b"  3) "a"

  ===== Set的集合运算命令 =====

  以下示例均基于：SADD s1 a b c      SADD s2 a b x

  • SINTER key1 key2 ...：求key1与key2的交集
      规则：SINTER key [key ...]
            可传多个key，求所有集合共有的元素；任一key不存在则结果为空集
      返回：交集元素列表（无序）
      示例：SINTER s1 s2          → 1) "a"  2) "b"
      扩展：SINTERSTORE dest key [key ...] 把交集结果存到dest

  • SDIFF key1 key2 ...：求key1与key2的差集
      规则：SDIFF key [key ...]
            以第一个key为基准，减去后面所有集合的元素；注意有方向，不可交换
      返回：差集元素列表（无序）
      示例：SDIFF s1 s2           → 1) "c"（在s1中但不在s2中）
            SDIFF s2 s1           → 1) "x"（顺序不同，结果不同）
      扩展：SDIFFSTORE dest key [key ...] 把差集结果存到dest

  • SUNION key1 key2 ...：求key1和key2的并集
      规则：SUNION key [key ...]
            合并所有集合的元素并自动去重
      返回：并集元素列表（无序）
      示例：SUNION s1 s2          → 1) "a"  2) "b"  3) "c"  4) "x"
      扩展：SUNIONSTORE dest key [key ...] 把并集结果存到dest

 */
public class SetRedis {

}
