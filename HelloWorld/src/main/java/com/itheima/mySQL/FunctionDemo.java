package com.itheima.mySQL;

public class FunctionDemo {
    /*
    函数：指一段可以直接被另一段程序调用的程序或代码；
        字符串函数
        数值函数
        日期函数
        流程函数

     1.字符串函数：用于处理字符串的函数
        常用的几个如下：
            concat(s1,s2,s3,...)：连接字符串，将s1 s2 s3 ... 连接成一个字符串
            lower(s)：将字符串s中的所有字符转换为小写
            upper(s)：将字符串s中的所有字符转换为大写
            lpad(str, n, pad)：左填充，用字符串pad对str的做盘进行填充，达到n个字符串长度
            rpad(str, n, pad)：将字符串str用字符串pad填充，从右边开始填充，直到str的长度达到n的长度
            substr(s1,start,len)：返回字符串s1中从start开始的len个字符
            length(s)：返回字符串s的长度
            trim(s)：将字符串s中的前后空格去掉

            SELECT 函数(参数);

         ------------------ 函数演示 ------------------
           select concat('hello', ' mysql', '!!!'); //  hello mysql!!!
           select lower('Hello World'); //  hello world
           select upper('Hello World'); //  HELLO WORLD
           select lpad('hello', 10, 'x'); //  xxxxxhello
           select rpad('hello', 10, 'x'); //  helloxxxxx
           select substr('hello world', 6, 5); //  world
           select length('hello world'); //  11
           select trim(' hello world '); //  hello world

         需求：由于业务需求变更，企业员工的工号，统一为5位数，目前不足5位数的全部在前面补0，例如：00001
          update tb_user set workno = lpad(workno, 5, '0');

       2.数值函数：用于处理数值的函数
        常用的几个如下：
            abs(x)：返回x的绝对值
            ceil(x)：向上取整
            floor(x)：向下取整
            round(x, n)：返回x四舍五入到n位小数
            mod(x, y)：返回x除以y的余数
            rand()：返回0到1的随机数

       --------------------- 函数演示 ------------------
          select abs(-10); //  10
          select ceil(1.1); //  2
          select floor(1.1); //  1
          select round(1.123456, 3); //  1.123
          select mod(10, 3); //  1
          select rand(); // 0.7524541046027599

          需求：生成6位随机数
          select ceil(round(rand(), 6) * 1000000); // 有bug：可能生成5位数（rand()极小时，eg: 0.012345, round后*1000000 < 100000）
          select lpad(ceil(round(rand(), 6) * 1000000), 6, '0'); // 针对于不足6位数的，使用lpad填充0
          select floor(rand() * 900000) + 100000;  // 另外一种写法：严格生成 100000~999999 的6位整数


       3.日期函数：用于处理日期的函数
        常用的几个如下：
            curdate()：返回当前日期
            curtime()：返回当前时间
            now()：返回当前日期和时间
            year(date)：返回日期date的年份
            month(date)：返回日期date的月份
            day(date)：返回日期date的日
            date_add(date, interval expr type)：返回一个日期/时间值加上一个时间间隔expr后的时间值
            datediff(date1, date2)：返回date1和date2之间的天数差

         ------------ 函数演示 ------------------
          select curdate(); // 2021-05-07
          select curtime(); // 09:05:07
          select now(); // 2021-05-07 09:05:07
          select year(now()); // 2021
          select month(now()); // 5
          select day(now()); // 7
          select date_add(now(), interval 1 month); // 2021-06-07 09:05:07
          select date_add(now(), interval 1 year); // 2022-05-07 09:05:07
          select date_add(now(), interval -1 year); // 2020-05-07 09:05:07
          select datediff(now(), '2021-05-01'); // 6


          需求：查询所有员工的入职天数，并根据入职天数倒序排序
          select name, datediff(curdate(), entrydate) as entrydays from tb_user order by entrydays desc;

      4.流程函数：用于处理流程的函数
        常用的几个如下：
           if(value, t, f) 如果value为true，则返回t，否则返回f

           ifnull(value1, value2)：如果value1不为空，返回value1，否则返回value2

           case when [value1] then [res1] ... else [default] end : 如果value1为true，则返回res1，... 否则返回default默认值

           case [expr] when [value1] then [res1] ... else [default] end : 如果expr等于value1，则返回res1，... 否则返回default默认值

           ----------- 函数演示 ----------
           select if(1 > 2, 'true', 'false'); // false
           select if(1 < 2, 'true', 'false'); // true
           select ifnull(null, 'hello'); // hello
           select ifnull(1, 'hello'); // 1
           select case when 1 > 2 then 'true' else 'false' end; //  false

           -- 多个 when 条件案例：根据分数划分等级（单科）
           select name,
               case
                   when score >= 90 then '优秀'
                   when score >= 75 then '良好'
                   when score >= 60 then '及格'
                   else '不及格'
               end as grade
           from student;

           -- 多科目场景：重复的 CASE 逻辑应封装为存储函数，避免冗余
           -- 创建函数
           create function get_grade(score int)
           returns varchar(10)
           deterministic
           begin
               return case
                   when score >= 90 then '优秀'
                   when score >= 75 then '良好'
                   when score >= 60 then '及格'
                   else '不及格'
               end;
           end;

           -- 调用函数：每门学科直接传分数
           select name,
               get_grade(math_score)    as math_grade,
               get_grade(english_score) as english_grade,
               get_grade(java_score)    as java_grade
           from student;

           -- 删除函数
           drop function if exists get_grade;




       BEGIN ... END 是 MySQL 存储函数/存储过程的代码块边界，作用类似 Java 里的 {}，把多条语句包起来作为一个整体执行。
       - DETERMINISTIC：告诉 MySQL 相同输入永远返回相同结果，有助于查询优化
      - RETURNS / RETURN：前者声明返回类型，后者是实际返回值
      - 调用时和内置函数写法完全一样，get_grade(math_score) 即可

          create function get_grade(score int)
          returns varchar(10)
          deterministic
          begin                        -- 函数体开始（相当于 Java 的 {）
              return case
                  when score >= 90 then '优秀'
                  else '不及格'
              end;
          end;                         -- 函数体结束（相当于 Java 的 }）



           -- 多个 when 条件案例（等值匹配）：根据工作地址显示简称
           select name, workaddress,
               case workaddress
                   when '北京' then '京'
                   when '上海' then '沪'
                   when '广州' then '穗'
                   when '深圳' then '深'
                   else '其他'
               end as addr_short
           from tb_emp;

            -- 需求：查询员工姓名和工作地址（北京或者上海展示为 一线城市，其余城市展示为二线城市）
            -- 注意：简单 CASE 的 WHEN 只支持等值匹配，不能写 OR；多值判断需改用搜索式 CASE
            select name, workaddress,
                case
                    when workaddress in ('北京', '上海') then '一线城市'
                    else '二线城市'
                end as addr_class
            from tb_user;

     */
}
