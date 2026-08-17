package com.itheima.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET_KEY ="aXRoZWltYQ=="; // 密钥
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000; // 12小时

    /**
     * 生成JWT令牌
     * @param claims 令牌中包含的信息
     * @return 生成的eJWT令牌字符串
     */
    public static String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .compact();
    }


    /**
     * 解析JWT令牌
     */
    public static void parseToken(String token){
        Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
