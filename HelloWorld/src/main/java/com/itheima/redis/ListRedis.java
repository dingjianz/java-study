package com.itheima.redis;

/**
 * List类型

  Redis中的List类型与Java中的LinkedList类似，可以看做是一个双向链表结构。
  既可以支持正向检索和也可以支持反向检索。

  特征也与LinkedList类似：
  • 有序
  • 元素可以重复
  • 插入和删除快
  • 查询速度一般

 常用来存储一个有序数据，例如：朋友圈点赞列表、评论列表等。

  ===== List类型的常见命令 =====

  List的常见命令有：

  • LPUSH key element ...：向列表左侧插入一个或多个元素
      规则：LPUSH key element [element ...]
            key不存在时自动创建；多个元素依次左插，故最后一个参数排在最左
      返回：插入后列表的长度
      示例：LPUSH users 1 2 3     → 3，此时列表为 3 2 1

  • LPOP key：移除并返回列表左侧的第一个元素，没有则返回nil
      规则：LPOP key [count]
            count（Redis 6.2+）可一次弹出多个；列表空后key自动删除
      示例：LPOP users            → "3"，剩余 2 1
            LPOP users 2         → 1) "2"  2) "1"
            LPOP users           → (nil)

  • RPUSH key element ...：向列表右侧插入一个或多个元素
      规则：RPUSH key element [element ...]
            参数从左到右依次追加到列表尾部
      返回：插入后列表的长度
      示例：RPUSH users 1 2 3     → 3，此时列表为 1 2 3

  • RPOP key：移除并返回列表右侧的第一个元素
      规则：RPOP key [count]
            与LPOP方向相反，其余行为一致
      示例：RPOP users            → "3"，剩余 1 2

  • LRANGE key star end：返回一段角标范围内的所有元素
      规则：LRANGE key start stop
            角标从0开始，含头含尾；支持负数，-1表示最后一个元素
            角标越界不报错，自动截取有效部分
      示例：LRANGE users 0 -1     → 查询全部元素
            LRANGE users 1 2      → 第2到第3个元素

  • BLPOP和BRPOP：与LPOP和RPOP类似，只不过在没有元素时等待指定时间，而不是直接返回nil
      规则：BLPOP key [key ...] timeout
            timeout单位为秒，0表示无限期阻塞；可同时监听多个key
      返回：二元组（弹出元素所属的key，元素值）；超时返回 nil
      示例：BLPOP users 10        → 1) "users"  2) "1"
            BLPOP users 10        → (nil)（10秒内无元素）
      场景：常用于实现阻塞式消息队列

 */
public class ListRedis {

}
