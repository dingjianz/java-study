package com.itheima.mySQL;

public class DQLDemo {
    /*
    DQL 语言（Data Query Language）,用来对数据库中表进行查询操作。

    DQL: 查询数据
        SELECT
            字段列表
        FROM
            表名列表
        WHERE
            条件列表
        GROUP BY
            分组字段列表
        HAVING
            分组后条件列表
        ORDER BY
            排序字段列表
         LIMIT
            分页参数

     DQL: 基本查询
        1.查询多个字段
            SELECT 字段1, 字段2, ... FROM 表名; // 查询指定字段
            SELECT * FROM 表名; // 查询所有字段
           eg: SELECT id, name, age, gender FROM tb_user;

         2. 设置别名
         SELECT 字段1 AS 别名1, 字段2 AS 别名2, ... FROM 表名;
         eg: SELECT id AS id1, name AS "姓名", age AS "年龄", gender AS "性别" FROM tb_user;
               其中 AS 关键字可以省略
            +------+----------+--------+--------+
            | id1  | 姓名     | 年龄   | 性别   |
            +------+----------+--------+--------+
            |    1 | zhangsan |     32 | 男     |
            |    2 | wangwu   |     17 | 男     |
            |    3 | 里斯     |     60 | 女     |
            |    4 | 赵敏     |     25 | 女     |
            +------+----------+--------+--------+

         3.去除重复记录
            select distinct 字段1, 字段2, ... from 表名;
            eg: select distinct gender from tb_user;
                +--------+
                | gender |
                +--------+
                | 男     |
                | 女     |
                +--------+


        DQL：条件查询
           1.语法
           SELECT 字段列表 FROM 表名 WHERE 条件列表;
           2.条件
                2.1. 比较运算符
                  = : 等于
                  <> : 不等于
                  < : 小于
                  > : 大于
                  <= : 小于等于
                  >= : 大于等于
                  <> 或 != : 不等于
                  BETWEEN A AND B :  A <= x <= B
                  LIKE 占位符：模糊匹配（ _ 匹配一个字符，% 匹配任意多个字符）
                      eg: SELECT * FROM tb_user WHERE name LIKE '张%'; // 查询姓名以张开头的
                      SELECT * FROM tb_user WHERE name LIKE '%张'; // 模糊匹配姓名以张结尾的
                      SELECT * FROM tb_user WHERE name LIKE '%张%'; // 模糊匹配姓名中包含张的

                      SELECT * FROM tb_user WHERE name LIKE '张%' OR name LIKE '%张'; // 模糊匹配姓名中包含张的
                      SELECT * FROM tb_user WHERE name LIKE '_张%'; // 查询姓名中第二个字符是张的
                      SELECT * FROM tb_user WHERE name LIKE '张_'; // 字段以"张"开头且后面恰好跟一个字符的所有记录

                      SELECT * FROM tb_user WHERE name LIKE '__'; // 查询姓名长度为2的


                  IS NULL : 为NULL
                  IS NOT NULL : 不为NULL
                  IN (A, B, C) : A, B, C 中的任意一个
                  NOT IN (A, B, C) : A, B, C 中的任意一个

              2.2.逻辑运算符
                  AND  或 && : 并且
                  OR  或 || : 或者
                  NOT  或 ! : 非
                  括号 ()  改变运算顺序
                  eg: SELECT * FROM tb_user WHERE age > 18 AND gender = '男';


         DQL: 聚合函数
            将一列数据作为一个整体，进行纵向计算；
            语法：SELECT 聚合函数(字段) FROM 表名;
             细节：null值不参与所有聚合函数运算

            常见的聚合函数
              COUNT() : 统计个数
              SUM() : 求和
              AVG() : 平均值
              MAX() : 最大值
              MIN() : 最小值

              select count(*) from tb_user; // 统计总记录数
              select avg(age) from tb_user; // 平均年龄
              select sum(age) from tb_user; // 总年龄
              select max(age) from tb_user; // 最大年龄
              select min(age) from tb_user; // 最小年龄
              select count(*) from tb_user where age < 60 and gender = "男"; // 统计小于60岁的男性的个数
              select sum(age) from tb_user where gender = "男"; // 统计男性的总年龄

         DQL: 分组查询
            语法：SELECT 分组字段列表 FROM 表名 [WHERE 条件] GROUP BY 分组字段列表 [HAVING 分组后过滤条件];

            细节：where 和 having 的区别
                    执行时机不同：where是分组之前进行过滤，不满足where条件，不参与分组；
                                having是分组之后对结果进行过滤。
                     判断条件不同：where不同对聚合函数进行判断，而having可以。

                eg: SELECT gender, count(*) FROM tb_user GROUP BY gender; // 查询每个性别的个数
                    +--------+----------+
                    | gender | count(*) |
                    +--------+----------+
                    | 男     |        3 |
                    | 女     |        2 |
                    +--------+----------+

                select name, count(*) from tb_user group by gender;
                    +----------+
                    | count(*) |
                    +----------+
                    |        3 |
                    |        2 |
                    +----------+
     */
}
