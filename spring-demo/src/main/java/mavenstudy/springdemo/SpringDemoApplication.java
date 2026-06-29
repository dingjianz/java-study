package mavenstudy.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类/引导类
 */
@SpringBootApplication
public class SpringDemoApplication {
/*
     起步依赖：
        spring-boot-starter-web：SpringBoot默认启动依赖 包含了web应用开发所需要的常见依赖
        spring-boot-starter-test：包含了单元测试所需要的常见依赖

     http协议：超文本传输协议
     特点：基于TCP协议，面向连接，安全。
     基于请求-响应模型的，一次请求对应一次响应。
     http协议是无状态的协议：对于事务处理没有记忆能力。每次请求-响应都是独立的，
            缺点：多次请求间不能共享数据。
            优点：速度快。

         host: 请求的主机名
         user-agent: 请求的浏览器信息
         accept: 表示浏览器能接收的资源类型，如：text/html image/*
         accept-language: 浏览器希望接收的语言
         accept-encoding: 表示浏览器可以支持的压缩类型，例如gzip、deflate等
         content-type：请求主体的数据类型
         content-length: 请求主体的长度

         请求方式 GET:请求参数在请求行中，没有请求体，如/brand?id=1&name=OPPO，限制请求参数的个数和格式，请求大小在浏览器中是有限制的。
         POST请求：请求参数在请求体中，如/brand 不限制请求参数的个数和格式，请求大小没限制

         http协议-请求数据获取
         web服务器(Tomcat)对http协议的请求数据进行解析，并进行了封装(HttpServletRequest)，在调用Controller方法的时候传递给了该方法。
         这样，就使得程序员不必对协议进行操作，让web开发更加敏捷。





 */
    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
