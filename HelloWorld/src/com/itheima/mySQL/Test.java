package com.itheima.mySQL;

public class Test {
    /*
    根据需求，完成SQL语句的编写
        1.查询员工的姓名、年龄、职位、部门信息(隐式内连接)。
          select e.name, e.salary, d.name from employe e, dept d where e.dept_id = d.id;

        2.查询年龄小于30岁的员工姓名、年龄、职位、部门信息（显示内连接）。
          select e.name, e.age, e.position, d.name from employee e inner join dept d on e.dept_id = d
          .id where e.age < 30;

        3.查询拥有员工的部门ID、部门名称。
           select d.id, d.name from dept d where d.id in (select e.dept_id from employe e);
           select distinct d.id, d.name from employe e, dept d where d.id = e.dept_id;

        4.查询所有年龄大于40岁的员工，及其归属的部门名称;如果员工没有分配部门，也需要展示出来。
          select e.*, d.name from employe e left join dept d on e.dept_id = d.id where e.age > 40;

        5.查询所有员工的工资等级。
        select e.name, s.grade as '工资等级' from employe e, salgrade s where e.salary between s.losal and s.hisal;

        6.查询"研发部"所有员工的信息及工资等级。

        7.查询"研发部”员工的平均工资。
        8.查询工资比"灭绝"高的员工信息。
        9.查询比平均薪资高的员工信息。
        10.查询低于本部门平均工资的员工信息。
        11.查询所有的部门信息，并统计部门的员工人数。
        12.查询所有学生的选课情况，展示出学生名称，学号，课程名称

        create table salgrade(
           grade int comment '等级',
           losal int comment '最低薪资',
           hisal int comment '最高薪资'
        ) comment '薪资等级表';

        insert into salgrade values (1, 0, 3000), (2, 3001, 5000), (3, 5001, 8000),
         (4, 8001, 10000), (5, 10001, 15000), (6, 15001, 20000), (7, 20001, 25000), (8, 25001, 30000);
     */
}
