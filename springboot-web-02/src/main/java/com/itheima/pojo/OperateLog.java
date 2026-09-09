package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperateLog {
    @TableId(type = IdType.AUTO) // 主键由数据库自增生成，不用 MP 默认的雪花 ID
    private Integer id; // ID
    private Integer operateEmpId; // 操作人员id
    private LocalDateTime operateTime; // 操作时间
    private String className; // 操作类名
    private String methodName; // 操作方法名
    private String methodParams; // 操作方法参数
    private String returnValue; // 操作方法返回值
    private Long costTime; // 操作方法耗时

}
