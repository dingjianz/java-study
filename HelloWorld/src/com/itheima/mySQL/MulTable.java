package com.itheima.mySQL;

public class MulTable {
    /*
     create table student(
         id int primary key auto_increment comment '主键ID',
         name varchar(20) not null comment '学生姓名',
         no varchar(10) not null unique comment '学生学号'
     ) comment '学生表';

     insert into student(name,no) values('张三','001'),('李四','002'),('王五','003'),('赵六','004');

     create table course(
         id int primary key auto_increment comment '课程ID',
         name varchar(20) not null comment '课程名称'
     ) comment '课程表';

     insert into course(name) values('Java'),('C++'),('Python');

     创立中间表(针对于多对多的关系)
     create table student_course(
         id int primary key auto_increment comment '主键ID',
         studentid int not null comment '学生ID',
         courseid int not null comment '课程ID',
         constraint fk_courseid foreign key (courseid) references course(id),
         constraint fk_studentid foreign key (studentid) references student(id)
     ) comment '学生课程中间表';

     insert into student_course(studentid,courseid) values(1,1),(1,2),(1,3),(2,1),(2,2),(3,1),(3,3),(4,2);

    一对一的关系：
        案例：用户与用户详情的关系
        关系：一对一关系，多用于单表拆分，将一张表的基础字段放在一张表中，其余详情放在一张表中，以提升操作效率。
        实现：在任意一方加入外键，关联另外一方的主键，并且设置外键为唯一的(UNIQUE)

        create table tb_user(
            id int primary key auto_increment comment '用户ID',
            name varchar(10) not null comment '用户名',
            age int not null comment '年龄',
            gender char(1) not null comment '1:男 2:女',
            phone varchar(11) not null comment '手机号'
        ) comment '用户表';

        insert into tb_user(id, name,age,gender,phone) values(4, '张三',18,'1','18368469517'),
        (5, '李四',19,'2','18368469508'),
        (6, '王五',20,'1','18368469509'),
        (7, '赵六',21,'2','18368469510');

        create table tb_user_edu(
            id int primary key auto_increment comment '用户详情ID',
            degree int not null comment '用户学历',
            major varchar(50) not null comment '专业',
            primaryschool varchar(50) not null comment '小学',
            middleschool varchar(50) not null comment '中学',
            university varchar(50) not null comment '大学',
            userid int unique comment '用户ID',
            constraint fk_userid foreign key (userid) references tb_user(id)
        ) comment '用户教育信息表';

        insert into tb_user_edu(degree,major,primaryschool,middleschool,university,userid) values(1,'Java','星火小学',
        '东升私立高中','哈佛大学',4),
        (2,'C++','宝福小学','新安中学','耶鲁大学',5),
        (3,'Python','安居苑小学','秀山中学','辽石化大学',6),
        (4,'Java','火炬小学','高河中学','斯坦福大学',7);

        多表查询: 指的是从多张表中查询数据
        笛卡尔积：笛卡尔乘积是指在数学中，两个集合A和B集合的所有组合的情况。(在多表查询时，需要消除无效的笛卡尔积)
        无效的笛卡尔积：指的时在多表查询时，查询条件没有限制，导致查询出无效的数据。
        eg: select * from employe dept where employe.dept_id = dept.id;

        多表查询分类
            1.连接查询
                1.1.内连接查询：相当于查询A、B两张表中交集部分数据。
                    1.1.1 隐式内连接
                        select 字段列表 from 表1, 表2 where 条件
                        select e.name, d.name from employee e, dept d where e.dept_id = d.id;
                    1.1.2 显式内连接
                        select 字段列表 from 表1 [inner] join 表2 on 连接条件...; // inner 可以省略
                        select e.name, d.name from employee e inner join dept d on e.dept_id = d.id;

                1.2.外连接查询：
                    1.2.1 左外连接查询：查询左表所有数据，以及两张表交集部分数据。
                        select 字段列表 from 表1 left [outer] join 表2 on 连接条件...;
                        select e.*, d.name from employe e left outer join dept d on e.dept_id = d.id;

                    1.2.2 右外连接查询：查询右表所有数据，以及两张表交集部分数据。
                        select 字段列表 from 表1 right [outer] join 表2 on 连接条件...;
                        select d.*, e.* from employe e right outer join dept d on e.dept_id = d.id;

                        左外连接查询用的比较多，因为右外连接可以转换为左外连接。

                1.3.自连接查询：当前表与自身的连接查询，自连接必须使用表别名。
                自连接查询可以是内连接查询，也可以是外连接查询。
                select 字段列表 from 表A 别名A join 表A 别名B on 连接条件...;
                案例1：employe表与自己进行连接，查询出所有员工的姓名以及员工的上司的姓名。
                select e.name, m.name from employe e inner join employe m on e.manager_id = m.id;

                案例2:查询所有员工employe 及其领导的名字 employe,如果员工没有领导，也需要查询出来。
                select a.name as '员工', b.name as '领导' from employe a left outer join employe b on a.manager_id = b.id;

            2.联合查询-union, union all
                对于union查询，就是把多次查询的结果合并起来，形成一个新的查询结果集。
                union查询默认会去重，如果需要保留重复数据，可以使用union all
                语法：
                select 字段列表 from 表1 where 条件1
                union [all]
                select 字段列表 from 表2 where 条件2;

                注意：1.两个查询的字段数量必须一致
                    2.两个查询的字段对应的数据类型必须兼容

                案例：将薪资低于 5000的员工 以及 年龄大于50的员工全部查询出来。
                select name, salary from employe where salary < 5000
                union
                select name, salary from employe where age > 50;

            3.子查询：SQL语句中嵌套SELECT语句，称为嵌套查询，又称子查询。
            select * from 表1 where column1 = (select column1 from 表2);
            子查询外部的语句可以是insert / update / delete / select 中的任何一种。

            根据子查询的结果不同，分为：
                1.标量子查询：子查询结果为单个值
                2.行子查询：子查询结果为一行
                3.列子查询：子查询结果为一列
                4.表子查询：子查询结果为多行多列

             根据子查询位置，分为：where之后、from之后、select之后

           1.标量子查询：子查询返回的结果是单个值（数字、字符串、日期等），最简单的形式。
               常用的操作符：= <> > >= < <=

               案例1：查询 “销售部” 的所有员工信息。分为两步：第一查询销售部的部门id，第二根据销售部的部门id，查询员工信息。
               select * from employe where dept_id = (select id from dept where name = '销售部');

               案例2:查询在 “张三” 入职之后的员工信息。
               select * from employe where entrydate > (select entrydate from employe where name = '张三');
               注解：子查询 select entrydate from employe where name = '张三' 返回的是一个日期。

           2.行子查询：子查询返回的结果是一行（可以多列），这种子查询称为行子查询。
               常用的操作符： = <> in、not in

               案例1:查询与张无忌的薪资、直属上级都相同的员工信息。
               select * from employe where (salary, manager_id) = (select salary, manager_id from employe where name = '张无忌');
               注解: 子查询 select salary, manager_id from employe where name = '张无忌'; 子查询返回的结果是一行

           3.列子查询：子查询返回的结果是一列（可以是多行），这种子查询称为列子查询。
               常用的操作符： in、not in、any、some、all

               案例1：查询销售部和市场部的所有员工信息。
               select * from employe where dept_id in (select id from dept where name in ('销售部','市场部'));
               注解：子查询 select id from dept where name in ('销售部','市场部') 返回的是一列2行。

               案例2:查询比 财务部 所有人工资都高的员工信息。
               select * from employe where salary > all (select salary from employe where dept_id = (select id from dept where name = '财务部'));
               注解：子查询 select salary from employe where dept_id = (select id from dept where name = '财务部') 返回的一列多行。
               使用 MAX()
               select * from employe where salary > (select max(salary) from employe where dept_id = (select id from dept where name = '财务部'));

               案例3:查询 比研发部 任意一人工资高的员工信息。
               select * from employe where salary > any (select salary from employe where dept_id = (select id from dept where name = '研发部'));
               注解：子查询 select salary from employe where dept_id = (select id from dept where name = '研发部') 返回的一列多行。
               使用 MIN()（比任意一人高 = 比最低工资还高）
               select * from employe where salary > (select min(salary) from employe where dept_id = (select id from dept where name = '研发部'));

            4.表子查询：子查询返回的结果是多行多列，这种子查询称为表子查询。
                常用的操作符： in

                案例1：查询与张无忌、周芷若的职位和薪资相同的员工信息。
                select * from employe where (job, salary) in (select job, salary from employe where name in ('张无忌','周芷若'));
                注解: 子查询 select job, salary from employe where name in ('张无忌','周芷若') 返回的多列多行。

                案例2:查询入职日期是“2006-01-01”之后的员工信息，及其部门的信息。
                select e.*, d.* from (select * from employe where entrydate > '2006-01-01') e left join dept d on e
                .dept_id = d.id;
                注解: 子查询 select * from employe where entrydate > '2006-01-01' 返回的多列多行。


     */
}
