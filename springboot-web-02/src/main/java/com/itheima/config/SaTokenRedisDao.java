package com.itheima.config;

import cn.dev33.satoken.dao.SaTokenDaoForRedisTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis 5.x 兼容的 Sa-Token DAO。
 *
 * Sa-Token 1.46.0 默认使用 SET ... KEEPTTL 更新会话，
 * 但 KEEPTTL 仅在 Redis 6.0 及以上支持。这里保留原 TTL 后再写值，
 * 使登录会话可以在当前 Redis 版本中正常创建和更新。
 */
@Component
@Primary
public class SaTokenRedisDao extends SaTokenDaoForRedisTemplate {

    @Override
    public void update(String key, String value) {
        String finalKey = wrapKey(key);
        long expireMillis = stringRedisTemplate.getExpire(finalKey, TimeUnit.MILLISECONDS);
        if (expireMillis == NOT_VALUE_EXPIRE) {
            return;
        }
        if (expireMillis == NEVER_EXPIRE) {
            stringRedisTemplate.opsForValue().set(finalKey, value);
            return;
        }
        stringRedisTemplate.opsForValue().set(finalKey, value, expireMillis, TimeUnit.MILLISECONDS);
    }
}
