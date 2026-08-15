package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT（JSON Web Token）测试类
 *
 * ============================================================
 * JWT 介绍
 * ============================================================
 * JWT 是一种用于双方之间传递安全信息的简洁的、URL 安全的表述性声明规范。
 * 它是一种基于 JSON 的开放标准（RFC 7519），用于在网络应用环境间传递声明。
 *
 * JWT 的组成结构（三部分，用 . 分隔）：
 *
 * 1. Header（头部）：包含令牌类型（typ）和加密算法（alg）
 *    示例：{ "alg": "HS256", "typ": "JWT" }
 *
 * 2. Payload（载荷）：包含声明信息（claims），如用户信息、过期时间等
 *    示例：{ "id": "1", "username": "admin", "exp": 1786811785 }
 *
 * 3. Signature（签名）：对 Header 和 Payload 进行签名，防止数据篡改
 *    算法：HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
 *
 * JWT 的优势：
 * - 无状态：服务器不需要存储 Session，适合分布式系统
 * - 跨域友好：可以在不同域名之间传递
 * - 性能好：减少了数据库查询，提高了系统性能
 * - 移动端友好：适合移动应用的身份验证
 *
 * ============================================================
 * 重要注意事项
 * ============================================================
 *
 * 1. 【安全】密钥保护
 *    ❌ 不要将密钥硬编码在代码中（本示例仅用于测试）
 *    ✅ 使用配置文件或环境变量存储密钥
 *    ✅ 密钥要足够复杂，建议至少 32 位随机字符串
 *    ✅ 定期更换密钥，增强安全性
 *
 * 2. 【安全】敏感信息
 *    ❌ 不要在 Payload 中存储密码、敏感个人信息
 *    ✅ JWT 的 Payload 只是 Base64 编码，不是加密，任何人都可以解码查看
 *    ✅ 只存储必要的、非敏感的用户标识信息
 *
 * 3. 【时效】过期时间设置
 *    ✅ 必须设置合理的过期时间（exp），防止令牌被长期滥用
 *    ✅ 短期令牌（如 2 小时）+ 刷新令牌机制，平衡安全与用户体验
 *    ✅ 解析时要验证过期时间，过期的令牌应被拒绝
 *
 * 4. 【传输】HTTPS 使用
 *    ✅ 生产环境必须使用 HTTPS 传输 JWT，防止令牌被窃取
 *    ✅ 通常放在 HTTP 请求头中：Authorization: Bearer {token}
 *
 * 5. 【验证】签名验证
 *    ✅ 每次接收到 JWT 都必须验证签名，确保数据未被篡改
 *    ✅ 使用与生成时相同的密钥进行验证
 *    ✅ 捕获并处理验证失败的异常（如过期、签名错误）
 *
 * 6. 【存储】客户端存储
 *    Web：推荐存储在 HttpOnly Cookie 中（防 XSS），或使用 sessionStorage（防 CSRF）
 *    移动端：使用安全的存储机制（如 Keychain、KeyStore）
 *    ❌ 避免使用 localStorage，容易受到 XSS 攻击
 *
 * 7. 【注销】令牌失效
 *    JWT 本身是无状态的，无法主动撤销
 *    方案1：使用黑名单机制（Redis 存储已注销的令牌，直到过期）
 *    方案2：使用短期令牌 + 刷新令牌，撤销刷新令牌即可
 *
 * 8. 【算法】加密算法选择
 *    对称加密：HS256（HMAC SHA-256，密钥需保密）
 *    非对称加密：RS256（RSA SHA-256，使用公钥验证，私钥签名）
 *    ✅ 生产环境推荐使用 RS256，安全性更高
 *
 * ============================================================
 * 实际应用流程
 * ============================================================
 * 1. 用户登录 → 验证用户名密码
 * 2. 验证成功 → 服务器生成 JWT 并返回给客户端
 * 3. 客户端存储 JWT（如 Cookie、Header）
 * 4. 后续请求 → 客户端携带 JWT 访问受保护的资源
 * 5. 服务器验证 JWT → 验证通过则允许访问，失败则返回 401
 *
 * @author itheima
 * @see <a href="https://jwt.io/">JWT 官方网站</a>
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc7519">RFC 7519</a>
 */
public class JWTTest {
    /*
        生成JWT令牌
     */
    @Test
    public void testGenerateJWT() {
        Map<String,Object> map = new HashMap<>();
        map.put("id","1");
        map.put("username", "admin");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"aXRoZWltYQ==") // 指定加密算法 密钥
                .addClaims(map) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间为1min 后
                .compact(); // 生成令牌
        System.out.println(jwt);
    }

    /*
        解析JWT令牌
     */
    @Test
    public void testParseJWT() {
        String Token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJ1c2VybmFtZSI6ImFkbWluIiwiZXhwIjoxNzg2ODExNzg1fQ.mGfc5hLh664gQOir1-JctCUpmosuOs5ybqkfEqWFKpM";
        Claims claims = Jwts.parser()
                .setSigningKey("aXRoZWltYQ==") // 指定密钥
                .parseClaimsJws(Token)
                .getBody();

        System.out.println(claims);
    }
}
