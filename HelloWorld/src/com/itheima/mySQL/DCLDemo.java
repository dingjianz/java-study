package com.itheima.mySQL;

public class DCLDemo {
    /*
    DCL 语言（Data Control Language）,用来管理数据库用户、控制数据库的访问权限。

    DCL:管理用户
        1.查询用户
            USE mysql;
            SELECT * FROM user;

        2.创建用户
            CREATE USER '用户名'@'主机' IDENTIFIED BY '密码';

        3.修改用户密码
            ALTER USER '用户名'@'主机' IDENTIFIED WITH mysql_native_password BY '新密码';

        4.删除用户
            DROP USER '用户名'@'主机';


        需求1：创建用户 jianding9 ，只能够在当前主机localhost访问，密码123456
        CREATE USER 'jianding9'@'localhost' IDENTIFIED BY '123456';

        需求2:创建用户 heima，可以在任意主机访问该数据库，密码 123456
        create user 'heima'@'%' identified by '123456'; // % 表示任意主机

        需求3:修改用户 heima 的访问密码为 1234；
        alter user 'heima'@'%' identified with caching_sha2_password by '1234';
        注解： MySQL 8.4+ 将 mysql_native_password 插件移除了默认加载，改用 caching_sha2_password 作为默认认证方式。

        需求4:删除用户 heima
        drop user 'heima'@'%';

     DCL:控制数据库的访问权限
        MySQL 中定义了很多种权限，但是常用的就以下几种：
            ALL, ALL PRIVILEGES: 所有权限
            SELECT: 查询数据
            INSERT: 插入数据
            UPDATE: 修改数据
            DELETE: 删除数据
            ALTER: 修改表
            DROP: 删除数据库/表/视图权限
            CREATE: 创建数据库/表权限

         1.查询权限
            show grants for '用户名'@'主机';

         2.授予权限
            grant 权限1, 权限2, ... on 数据库.表 to '用户名'@'主机';

         3.撤销权限
            revoke 权限1, 权限2, ... on 数据库.表 from '用户名'@'主机';
     */
}
