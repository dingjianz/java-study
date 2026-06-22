package com.itheima.mySQL;

public class DMLDemo {
    /*
    DML 语言（Data Manipulation Language）,用来对数据库中表的数据记录进行增删改操作。

    DML：添加数据
     insert into 表名 values(值1, 值2, ...); // 添加一条数据
     insert into 表名(字段1, 字段2, ...) values(值1, 值2, ...); // 添加一条数据，指定字段
     insert into 表名 values(值1, 值2, ...), (值1, 值2, ...), (值1, 值2, ...);   // 批量添加数据

    DML: 修改数据
      update 表名 set 字段1 = 值1, 字段2 = 值2, ...; // 修改所有数据
      update 表名 set 字段1 = 值1, 字段2 = 值2, ... where 字段1 = 值1; // 修改指定数据
      update 表名 set 字段1 = 值1, 字段2 = 值2, ... where 字段1 = 值1 and 字段2 = 值2; // 批量修改数据

    DML: 删除数据
      delete from 表名; // 删除所有数据
      delete from 表名 where 字段1 = 值1; // 删除指定数据

     */
}
