package com.itheima.controller;

import com.itheima.pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会话技术简介
 *
 * 会话（Session）：用户打开浏览器访问 Web 服务器的资源，会话建立，直到有一方断开连接，会话结束。
 * 一次会话中可以包含多次请求和响应。
 *
 * 会话跟踪：一种维护浏览器状态的方法，服务器需要识别多次请求是否来自于同一浏览器，
 * 以便在同一次会话的多次请求间共享数据。
 *
 * 为什么需要会话跟踪：HTTP 是无状态协议，每次请求相互独立，服务器无法区分多次请求是否来自同一浏览器，
 * 因此需要额外的技术来标识和保存会话状态。
 *
 * 常见的会话跟踪技术对比：
 *
 * 1. Cookie（客户端会话跟踪技术）：数据存储在浏览器端。
 *    服务器通过响应头 Set-Cookie 下发，浏览器后续请求自动通过请求头 Cookie 携带。
 *    优点：HTTP 协议原生支持，浏览器自动携带，无需手动处理。
 *    缺点：只能存字符串、大小有限（约 4KB）、数据在客户端不安全、移动端 App 无法使用、
 *         用户可以禁用 Cookie、跨域受限。
 *
 * 2. Session（服务端会话跟踪技术）：数据存储在服务器端，底层基于 Cookie 实现
 *    （服务器把 JSESSIONID 写入 Cookie，浏览器每次请求携带该 id，服务器据此找到对应的 Session 对象）。
 *    优点：数据存服务端更安全，可存任意类型对象。
 *    缺点：占用服务器内存、依赖 Cookie（禁用后失效）、
 *         集群/分布式环境下多台服务器间 Session 不共享（需 Session 复制或 Redis 等集中存储）。
 *
 * 3. 令牌技术（Token，如 JWT）：登录成功后服务端生成令牌返回给客户端，
 *    客户端自行存储（如 localStorage），后续请求手动携带（通常放在请求头中）。
 *    优点：支持 PC / 移动端等任意客户端、解决集群环境下的共享问题、服务端不存储令牌（减轻服务器压力）。
 *    缺点：需要自己实现令牌的生成、校验与续期逻辑。
 *    当前主流方案（前后端分离项目基本都用它）。
 *
 * 本类用于演示 Cookie 的设置与获取，见下方 /c1、/c2 接口。
 */
@RestController
public class SessionController {
    // 设置cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response) {
        response.addCookie(new Cookie("login_username", "itheima")); // 设置Cookie or 响应Cookie
        return Result.success();
    }

    // 获取cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login_username")) {
                // 输出name 为 login_username 的 cookie 的value
                System.out.println("login_username：" + cookie.getValue());
            }
        }
        return Result.success();
    }

    @GetMapping("/s1")
    public Result session1(HttpSession session) {
        // 往 session 中存储数据
        session.setAttribute("loginUser", "tom");
        return Result.success();
    }

    @GetMapping("/s2")
    public Result session2(HttpSession session) {
        // 从 session 中 获取数据
        Object loginUser = session.getAttribute("loginUser");
        return Result.success(loginUser);
    }
}
