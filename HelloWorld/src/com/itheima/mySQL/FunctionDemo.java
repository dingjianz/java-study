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
          update emp set workno = lpad(workno, 5, '0');
     */
}
