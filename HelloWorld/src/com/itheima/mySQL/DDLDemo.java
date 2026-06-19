package com.itheima.mySQL;

public class DDLDemo {
    /*
    ------------ DDL 数据库定义语言 (Data Definition Language) 用于定义数据库结构 -----------
    mysql -u root -p // 登录mysql dJ573828395
    exit; // 退出mysql
    show databases; // 显示所有数据库

        +--------------------+
        | Database           |
        +--------------------+
        | information_schema |
        | itheima            |
        | mysql              |
        | performance_schema |
        | sys                |
        +--------------------+
        5 rows in set (0.001 sec)

    create database itheima; // 创建数据库
    create database if not exists itheima; // 如果数据库不存在则创建
    create database itheima default charset utf8mb4; // 创建数据库并指定字符集
    drop database itheima; // 删除数据库
    drop database if exists itheima; // 如果数据库存在则删除

    use itheima; // 使用数据库
        Database changed // 表示成功切换数据库

    select database(); // 显示当前数据库
        +--------------------+
        | Database           |
        +--------------------+
        | itheima            |
        +--------------------+
        1 row in set (0.001 sec)

    show tables; // 显示当前数据库中的所有表
        Empty set (0.002 sec) // 表示当前数据库中没有表

        +-------------------+
        | Tables_in_itheima |
        +-------------------+
        | user              |
        +-------------------+
        1 row in set (0.001 sec) // 表示查询结果只有一行 只有一个表，user

    desc 表名; // 显示表结构
    show create table 表名; // 显示表的创建语句

    // DDL - 创建表
    create table 表名(
       字段1 字段1类型[COMMENT 字段1注释],
       字段2 字段2类型[COMMENT 字段2注释],
       ...
       字段n 字段n类型[COMMENT 字段n注释]
    )[COMMENT 表注释];

    eg: create table tb_user(
      id int comment '编号',
      name varchar(50) comment '姓名',
      age int comment '年龄',
      gender varchar(1) comment '性别'
  ) comment '用户表';

  几个要点说明：

  - varchar(50) 中的数字表示最大字符长度，必须指定。
  - 每个字段之间用逗号 , 隔开，最后一个字段后面不加逗号。
  - comment 是注释，可选，用来描述字段或表的含义。
  - 整条语句以分号 ; 结尾。

  执行后可以用以下命令查看表结构：

  desc tb_user;        -- 查看表的字段信息
        +--------+-------------+------+-----+---------+-------+
        | Field  | Type        | Null | Key | Default | Extra |
        +--------+-------------+------+-----+---------+-------+
        | id     | int         | YES  |     | NULL    |       |
        | name   | varchar(50) | YES  |     | NULL    |       |
        | age    | int         | YES  |     | NULL    |       |
        | gender | varchar(1)  | YES  |     | NULL    |       |
        +--------+-------------+------+-----+---------+-------+
        4 rows in set (0.003 sec)

  show create table tb_user;   -- 查看建表语句

DDL - 表操作 - 数据类型
     MySQL 中的数据类型有很多，主要分为三类：数值类型、字符串类型、日期时间类型。
     - 数值类型：
         tinyint - 小整数类型  -128 ~ 127(有符号范围SIGNED) or 0 ~ 255(无符号范围UNSIGNED)  1字节
         int - 整数类型  -2147483648 ~ 2147483647(有符号范围SIGNED) or 0 ~ 4294967295(无符号范围UNSIGNED)  4字节
         smallint - 小整数类型  -32768 ~ 32767(有符号范围SIGNED) or 0 ~ 65535(无符号范围UNSIGNED)  2字节
         bigint - 大整数类型  -9223372036854775808 ~ 9223372036854775807(有符号范围SIGNED) or 0 ~ 18446744073709551615(无符号范围UNSIGNED)  8字节
         float - 浮点数类型  4字节
         double - 双精度浮点数类型  8字节
         decimal - 依赖于M（精度）和D（标度）的值，例如decimal(10,2) 表示总共有10位数字，其中2位是小数，共12字节

            age tinyint unsigned comment '年龄',
            score double(4,1) comment '分数'
     - 字符串类型：
         char - 固定长度字符串类型  0 ~ 255 字节
         varchar - 可变长度字符串类型  0 ~ 65535 字节
         text - 文本类型  0 ~ 65535 字节
         blob - 二进制大对象  0 ~ 65535 字节
         tinyblob - 二进制小对象  0 ~ 255 字节
         mediumblob - 中等二进制对象  0 ~ 16777215 字节
         longblob - 长二进制对象  0 ~ 4294967295 字节
         tinytext - 二进制小对象  0 ~ 255 字节
         mediumtext - 中等文本对象  0 ~ 16777215 字节
         longtext - 长文本对象  0 ~ 4294967295 字节

     - 日期时间类型：
         date - 日期类型  YYYY-MM-DD  0000-00-00 ~ 9999-12-31
         time - 时间类型  HH:MM:SS  -838:59:59 ~ 838:59:59
         year - 年类型    YYYY        0000 ~ 9999
         datetime - 日期时间类型  YYYY-MM-DD HH:MM:SS  0000-00-00 00:00:00 ~ 9999-12-31 23:59:59
         timestamp - 时间戳类型  YYYY-MM-DD HH:MM:SS  1970-01-01 00:00:00 ~ 2038-01-19 03:14:07


DDL - 表操作 - 修改
    添加字段
        alter table 表名 add 字段名 字段类型[COMMENT 字段注释][约束];
        eg: alter table tb_user add addr varchar(100) comment '地址';

     修改字段类型
        alter table 表名 modify 字段名 新字段类型[COMMENT 新字段注释];
        eg: alter table tb_user modify age smallint comment '年龄';

     修改字段名和字段类型
        alter table 表名 change 旧字段名 新字段名 新字段类型[COMMENT 新字段注释][约束];
        如果字段名不用修改，新字段名不需要写，狗则报错
        eg: alter table tb_user change addr address varchar(100) comment '地址';

      删除字段
        alter table 表名 drop 字段名;
        eg: alter table tb_user drop addr;

      修改表名
        alter table 表名 rename to 新表名;
        eg: alter table tb_user rename to user;

      删除表
        drop table [if exists] 表名; // 删除表，如果表存在则删除，如果表不存在则不报错

      删除指定表，并重新创建该表
        truncate table 表名; // 删除表中的所有数据，但表结构保留
     */
}
