package com.itheima.redis;

/**
 * SortedSet类型

  Redis的SortedSet是一个可排序的set集合，与Java中的TreeSet有些类似，但底层数据结构却差别很大。
  SortedSet中的每一个元素都带有一个score属性，可以基于score属性对元素排序，
  底层的实现是一个跳表（SkipList）加 hash表。

  SortedSet具备下列特性：
  • 可排序
  • 元素不重复
  • 查询速度快
  因为SortedSet的可排序特性，经常被用来实现排行榜这样的功能。

  ===== SortedSet类型的常见命令 =====

  SortedSet的常见命令有：
  （以下示例均基于：ZADD stu 85 Lucy 89 Rose 82 Jack 95 Tom 78 Jerry）

  • ZADD key score member：添加一个或多个元素到sorted set，如果已经存在则更新其score值
      规则：ZADD key [NX|XX] [GT|LT] [CH] score member [score member ...]
            NX 只新增不更新，XX 只更新不新增，GT/LT 仅当新score更大/更小时才更新
      返回：新增的元素个数（更新不计数，加CH则返回被改动的总数）
      示例：ZADD stu 85 Lucy 89 Rose    → 2
            ZADD stu 90 Lucy            → 0（Lucy已存在，score更新为90）

  • ZREM key member：删除sorted set中的一个指定元素
      规则：ZREM key member [member ...]
            可一次删多个；不存在的元素忽略；元素全删完后key自动删除
      返回：真正删除的元素个数
      示例：ZREM stu Lucy               → 1

  • ZSCORE key member：获取sorted set中的指定元素的score值
      规则：ZSCORE key member
            元素不存在返回 nil
      示例：ZSCORE stu Rose             → "89"

  • ZRANK key member：获取sorted set中的指定元素的排名
      规则：ZRANK key member
            排名从0开始，按score升序计算；降序排名用 ZREVRANK
      示例：ZRANK stu Jack              → 1（Jerry 78 排第0）
            ZREVRANK stu Tom            → 0（降序时Tom分最高）

  • ZCARD key：获取sorted set中的元素个数
      规则：ZCARD key
            key不存在时返回0；时间复杂度O(1)
      示例：ZCARD stu                   → 5

  • ZCOUNT key min max：统计score值在给定范围内的所有元素的个数
      规则：ZCOUNT key min max
            默认闭区间；加 ( 表示开区间；-inf / +inf 表示无穷
      示例：ZCOUNT stu 80 90            → 3（82、85、89）
            ZCOUNT stu (80 +inf         → 4（大于80，不含80）

  • ZINCRBY key increment member：让sorted set中的指定元素自增，步长为指定的increment值
      规则：ZINCRBY key increment member
            increment可为负数实现自减；元素不存在时按score=0新增
      返回：自增后的score值
      示例：ZINCRBY stu 2 Jack          → "84"
            ZINCRBY stu -5 Tom          → "90"

  • ZRANGE key min max：按照score排序后，获取指定排名范围内的元素
      规则：ZRANGE key start stop [WITHSCORES]
            这里的 min max 是排名角标，从0开始，含头含尾，支持负数（-1为最后一个）
            默认score升序；降序用 ZREVRANGE
      示例：ZRANGE stu 0 2              → 1) "Jerry"  2) "Jack"  3) "Lucy"
            ZRANGE stu 0 -1 WITHSCORES  → 元素与score交替返回
            ZREVRANGE stu 0 2           → 分数最高的前3名

  • ZRANGEBYSCORE key min max：按照score排序后，获取指定score范围内的元素
      规则：ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count]
            按score筛选而非排名；语法同ZCOUNT，支持 ( 与 -inf/+inf
            降序用 ZREVRANGEBYSCORE key max min（注意 max 在前）
      示例：ZRANGEBYSCORE stu 80 90            → score在80~90之间的元素
            ZRANGEBYSCORE stu -inf +inf LIMIT 0 3   → 全范围取前3个

  • ZDIFF、ZINTER、ZUNION：求差集、交集、并集
      规则：ZDIFF numkeys key [key ...] [WITHSCORES]
            ZINTER numkeys key [key ...] [WITHSCORES]
            ZUNION numkeys key [key ...] [WITHSCORES]
            必须先写参与运算的key数量numkeys（Redis 6.2+）
            交集/并集中同一元素的score默认相加，可用 AGGREGATE MIN|MAX 改变
      示例：ZINTER 2 stu1 stu2                 → 两个集合共有的元素
            ZUNION 2 stu1 stu2 WITHSCORES     → 并集，score相加
            ZDIFF  2 stu1 stu2                → 在stu1但不在stu2中的元素
      扩展：ZINTERSTORE / ZUNIONSTORE / ZDIFFSTORE 可把结果存到新key

  注意：所有的排名默认都是升序，如果要降序则在命令的Z后面添加REV即可

      例如：ZRANK          →  ZREVRANK
            ZRANGE         →  ZREVRANGE
            ZRANGEBYSCORE  →  ZREVRANGEBYSCORE（注意参数变为 max min）

 */
public class SortedSetRedis {

}
