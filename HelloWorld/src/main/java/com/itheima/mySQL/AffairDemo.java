package com.itheima.mySQL;

public class AffairDemo {
    /*
    数据准备
    create table account(
        id int primary key auto_increment comment '主键id',
        name varchar(20) comment '账户名称',
        balance double comment '账户余额'
    ) comment '账户信息表';

    insert into account(id, name, balance) values(null, '张三', 2000),(null, '李四', 2000);

    // 恢复数据
    update account set balance = 2000 where name in ('张三', '李四');

    // 转账操作
     1.查询张三账户余额
     select balance from account where name = '张三';

     2.将张三账户余额-1000
     update account set balance = balance - 1000 where name = '张三';

     3.将李四账户余额+1000
     update account set balance = balance + 1000 where name = '李四';

     事务操作
         方式1：
            查看/设置事务提交方式
            select @@autocommit; // 0 表示事务提交方式为手动提交 1 表示事务提交方式为自动提交
            set @@autocommit = 0;

            提交事务
            commit;

            回滚事务
            rollback;

          方式2:
            开启事务
            start transaction 或 begin;

            提交事务
            commit;

            回滚事务
            rollback;

       事务的四大特性：ACID
           1.原子性(Atomicity)：事务是不可分割的最小操作单元，要么全部成功，要么全部失败
           2.一致性(Consistency)：事务完成时，必须使所有的数据都保持一致状态
           3.隔离性(Isolation)：数据库系统提供的隔离机制，保证事务在不受外部并发操作影响的独立环境下运行
           4.持久性(Durability)：事务一旦提交或回滚，它对数据库中的数据的改变就是永久的。

       事务的并发问题：
        1.脏读：一个事务读取了另一个事务未提交的数据
        2.不可重复读：一个事务在执行过程中，多次读取同一数据，由于其他事务的修改，导致读取的数据不一致
        3.幻读：一个事务在执行条件查询过程中，没有对应的数据行，但是在插入数据的时候，又发现这行数据存在，好像出现了“幻读”

        事务的隔离级别：
           1.READ UNCOMMITTED：脏读 不可重复读 幻读 都会出现
           2.READ COMMITTED(oracle默认)：不可重复读 幻读 会出现
           3.REPEATABLE READ(mySQL默认)：幻读 会出现
           4.SERIALIZABLE(串行化)：三个问题都不会出现，但是性能最差

           事务隔离级别越高 数据越安全 但是性能越低。

           查看事务隔离级别
           select @@transaction_isolation;

           设置事务隔离级别
           set [session|global] transaction isolation level [read uncommitted|read committed|repeatable read|serializable];
     */
}
