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

        Redis 命令帮助：
        help                   - 显示帮助信息
        help @<group>          - 查看某个命令组的所有命令
        help <command>         - 查看具体命令的用法

        常用命令分组：
        help @generic          - 通用命令（DEL、EXPIRE、KEYS、TTL 等）
        help @string           - String 类型命令（SET、GET、INCR 等）
        help @hash             - Hash 类型命令（HSET、HGET、HDEL 等）
        help @list             - List 类型命令（LPUSH、RPUSH、LPOP 等）
        help @set              - Set 类型命令（SADD、SMEMBERS、SINTER 等）
        help @sorted_set       - SortedSet 类型命令（ZADD、ZRANGE 等）
        help @geo              - GEO 类型命令（GEOADD、GEORADIUS 等）
        help @bitmap           - BitMap 类型命令（SETBIT、GETBIT 等）
        help @hyperloglog      - HyperLogLog 类型命令（PFADD、PFCOUNT 等）

        示例：
        127.0.0.1:6379> help @string
        127.0.0.1:6379> help SET

        --------------------------------------------------------------------

        通用命令（@generic）

        DEL key [key ...]
        - 删除一个或多个 key
        - 返回值：成功删除的 key 数量
        - 支持所有数据类型
        - 示例：DEL name
                DEL user:1 user:2 user:3    （批量删除）

        KEYS pattern
        - 查找符合模式的所有 key
        - 模式匹配符：
          * : 匹配任意多个字符（包括 0 个） 不建议在生产环境中使用
          ? : 匹配单个字符
          [] : 匹配括号内的任一字符
          \x : 转义特殊字符
        - 示例：KEYS *                     （查找所有 key，生产环境禁用！）
                KEYS user:*                （查找 user: 开头的 key）
                KEYS user:?                （user: 后只有 1 个字符）
                KEYS user:[123]            （user:1、user:2、user:3）
        - ⚠️ 警告：KEYS 会阻塞 Redis，数据量大时非常危险，生产环境禁用
        - 替代方案：使用 SCAN 命令（游标遍历，不阻塞）

        其他通用命令：
        EXISTS key [key ...]             - 判断 key 是否存在，返回存在的数量
        EXPIRE key seconds               - 设置 key 的过期时间（秒）
        EXPIREAT key timestamp           - 设置 key 在指定时间戳过期
        TTL key                          - 查看 key 剩余过期时间（秒），-1 永久，-2 不存在
        PTTL key                         - 查看 key 剩余过期时间（毫秒）
        PERSIST key                      - 移除 key 的过期时间，变为永久
        TYPE key                         - 查看 key 的数据类型
        RENAME key newkey                - 重命名 key（会覆盖 newkey）
        RENAMENX key newkey              - 重命名 key（newkey 不存在时才成功）
        SCAN cursor [MATCH pattern] [COUNT count]
                                         - 游标遍历 key，替代 KEYS，不阻塞

        示例：
        127.0.0.1:6379> EXISTS name age        （返回 2 表示都存在）
        127.0.0.1:6379> EXPIRE session:123 3600（1 小时后过期）
        127.0.0.1:6379> TTL session:123        （返回 3599）
        127.0.0.1:6379> TYPE name              （返回 string）
        127.0.0.1:6379> SCAN 0 MATCH user:* COUNT 100

        --------------------------------------------------------------------

        Redis 数据结构介绍

        Redis 是一个 key-value 的数据库，key 一般是 String 类型，
        不过 value 的类型多种多样：

        基本类型：
        | 类型      | value 示例                |
        |-----------|---------------------------|
        | String    | hello world               |
        | Hash      | {name: "Jack", age: 21}   |
        | List      | [A -> B -> C -> C]        |
        | Set       | {A, B, C}                 |
        | SortedSet | {A: 1, B: 2, C: 3}        |

        特殊类型：
        | 类型      | value 示例                |
        |-----------|---------------------------|
        | GEO       | {A: (120.3, 30.5)}        |
        | BitMap    | 0110110101110101011       |
        | HyperLog  | 0110110101110101011       |

        说明：
        - String：字符串，最基础的类型，可存文本、数字、二进制数据
        - Hash：哈希（散列），类似 Java 的 HashMap，适合存对象
        - List：列表，类似 LinkedList，有序、可重复，可当队列/栈用
        - Set：集合，类似 HashSet，无序、不可重复
        - SortedSet：有序集合（ZSet），每个元素带 score，按 score 排序，适合排行榜
        - GEO：地理坐标，基于 SortedSet 实现，存经纬度并支持距离计算
        - BitMap：位图，用二进制位记录状态，适合签到、布尔统计
        - HyperLogLog（HyperLog）：基数统计，用极小内存做海量数据去重计数（有误差）

        --------------------------------------------------------------------

        String 类型常用命令

        SET 与 MSET 的区别：

        SET key value [EX seconds] [PX milliseconds] [NX|XX]
        - 设置单个键值对
        - 可选参数：EX 设置过期秒数，PX 设置过期毫秒数
        - NX：key 不存在时才设置（用于添加）
        - XX：key 存在时才设置（用于更新）
        - 示例：SET name "张三"
                SET count 100 EX 60          （60秒后过期）
                SET lock "1" NX EX 10        （分布式锁常用）

        MSET key1 value1 key2 value2 ...
        - 批量设置多个键值对（Multi SET）
        - 原子操作，要么全部成功，要么全部失败
        - 性能优势：减少网络往返次数（RTT），比多次 SET 快
        - 示例：MSET name "张三" age 20 city "北京"

        GET 与 MGET：
        GET key                              - 获取单个值
        MGET key1 key2 ...                   - 批量获取多个值

        其他常用命令：
        INCR key                             - 数值加 1（原子操作）
        INCRBY key increment                 - 数值加指定值
        DECR key                             - 数值减 1
        DECRBY key decrement                 - 数值减指定值
        SETNX key value                      - 等同于 SET key value NX
        SETEX key seconds value              - 等同于 SET key value EX seconds
        STRLEN key                           - 获取字符串长度
        APPEND key value                     - 追加字符串
        GETRANGE key start end               - 获取子串
        SETRANGE key offset value            - 替换子串

        使用场景：
        - 缓存对象的 JSON 字符串
        - 计数器（INCR）：点赞数、访问量
        - 分布式锁（SET key value NX EX）
        - Session 共享
     */
}


