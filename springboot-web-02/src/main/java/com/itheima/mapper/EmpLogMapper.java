package com.itheima.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pojo.EmpLog;
import com.itheima.pojo.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpLogMapper {
    @Insert("insert into emp_log (info) values (#{info})")
    void insert(String info);

    @Select("select * from emp_log")
    IPage<EmpLog> getAllEmpLogs(IPage<EmpLog> page);
}
