package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pojo.EmpLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpLogMapper extends BaseMapper<EmpLog> {

    @Select("select * from emp_log")
    IPage<EmpLog> getAllEmpLogs(IPage<EmpLog> page);

    // 原来这里有 insert(String info)，方法名与 BaseMapper.insert(T) 冲突
    // （MyBatis 按方法名注册语句，重载会导致语句 id 重复），
    // 已改为在 service 里组装 EmpLog 实体后调用 BaseMapper.insert(empLog)，
    // operate_time 由 MyMetaObjectHandler 自动填充
}
