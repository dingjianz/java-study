package com.itheima.config;

import cn.dev33.satoken.json.SaJsonTemplateForJackson;
import cn.dev33.satoken.exception.SaJsonConvertException;
import cn.dev33.satoken.util.SaFoxUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Sa-Token JSON 序列化配置。
 *
 * Sa-Token 官方 Jackson 模板默认开启全局多态类型，因此 Redis JSON 中会出现
 * "@class"。Sa-Token 读取 SaSession 时会明确传入目标类型，所以这里关闭全局
 * 多态类型，使用普通 Jackson 字段映射即可完成会话的序列化和反序列化。
 */
@Component
@Primary
public class SaTokenJsonTemplate extends SaJsonTemplateForJackson {

    private final ObjectMapper legacyObjectMapper;

    public SaTokenJsonTemplate() {
        // 仅用于读取升级前已经写入 Redis 的带 @class 数据。
        legacyObjectMapper = new SaJsonTemplateForJackson().objectMapper;
        objectMapper.deactivateDefaultTyping();
    }

    @Override
    public <T> T jsonToObject(String jsonStr, Class<T> type) {
        if (SaFoxUtil.isEmpty(jsonStr)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonStr, type);
        } catch (JsonProcessingException currentFormatException) {
            try {
                return legacyObjectMapper.readValue(jsonStr, type);
            } catch (JsonProcessingException legacyFormatException) {
                throw new SaJsonConvertException(legacyFormatException);
            }
        }
    }
}
