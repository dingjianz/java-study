package com.itheima.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus 自动填充处理器
 * 配合实体字段上的 @TableField(fill = ...) 注解，在新增/修改时自动填充值
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增数据时触发（对应 FieldFill.INSERT 和 FieldFill.INSERT_UPDATE）
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 同一条记录的 createTime / updateTime 复用同一个时间，避免出现纳秒级差异
        LocalDateTime now = LocalDateTime.now();
        // strictInsertFill：字段名、类型都匹配且当前值为空时才填充
        this.strictInsertFill(metaObject, "operateTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);
    }

    /**
     * 修改数据时触发（对应 FieldFill.UPDATE 和 FieldFill.INSERT_UPDATE）
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // EmpLog 目前只有 INSERT 填充，暂无 UPDATE 字段
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }
}
