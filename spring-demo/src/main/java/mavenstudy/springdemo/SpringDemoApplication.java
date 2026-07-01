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

     http协议 - 响应数据格式
     1xx: 响应中-临时状态码，表示请求已经接收，告诉客户端应该继续请求，或者如果它已经完成则忽略它.
            例如websocket请求，服务器会返回101状态码，表示升级协议，之后服务器会返回请求行、请求头和请求体。

     2xx: 成功-表示请求已经被成功接收，处理已完成。
     3xx: 重定向-重定向到其他地方；让客户端再发起一次请求以完成整个处理。
     4xx: 客户端错误-处理发生错误，责任在客户端，如请求了不存在的资源等。
     5xx: 服务器错误-处理发生错误，责任在服务器，如服务器内部错误、程序抛出异常等。

     常见的响应状态码：
     200 OK：           请求成功，服务器已正常返回所请求的资源（最常见的成功状态码）。
     301 Moved Permanently：永久重定向，请求的资源已被永久移动到新的 URL，浏览器/搜索引擎会更新书签和索引。
     302 Found：        临时重定向，资源临时位于另一个 URL，后续请求仍使用原地址（如登录后跳转、表单提交后跳转）。
     304 Not Modified： 资源未修改，客户端可直接使用本地缓存，无需重新下载（配合协商缓存使用，节省带宽）。
     400 Bad Request：  客户端错误，请求语法/参数有误，服务器无法理解（如参数格式不正确、缺少必填参数）。
     401 Unauthorized： 未认证，请求要求身份认证，客户端未提供有效凭证（如未登录、token 缺失或失效）。
     403 Forbidden：    禁止访问，服务器理解请求但拒绝执行，客户端没有访问权限（已认证但无对应资源的权限）。
     404 Not Found：    未找到，服务器找不到请求的资源（如 URL 写错、资源已删除）。
     405 Method Not Allowed：请求方法不被允许（如接口只支持 GET，却用 POST 访问）。
     428 Precondition Required：要求先决条件，服务器要求请求必须携带条件头（如 If-Match），用于防止"丢失更新"问题（多个客户端并发修改同一资源时覆盖彼此的修改）。
     429 Too Many Requests：请求过多，客户端在给定时间内发送了太多请求，触发了限流（常用于限流/防刷，响应可携带 Retry-After 头告知多久后可重试）。
     431 Request Header Fields Too Large：请求头字段太大，服务器拒绝处理因为请求头太大（单个头字段过大或所有头加起来超限），常见原因是 Cookie 过多过大或携带超长 token。
     500 Internal Server Error：服务器内部错误，服务器在处理请求时发生异常（如代码抛出未捕获的异常、空指针）。
     502 Bad Gateway：  网关错误，作为网关/代理的服务器从上游服务器收到了无效响应（如后端服务挂了）。
     503 Service Unavailable：服务不可用，服务器暂时无法处理请求（如服务过载、正在维护）。
     504 Gateway Timeout：网关超时，作为网关/代理的服务器未能及时从上游服务器收到响应。


 */



    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
