package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpLog {
    @TableId(type = IdType.AUTO) // 主键由数据库自增生成，不用 MP 默认的雪花 ID
    private Integer id;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime operateTime;
    private String info;
}
