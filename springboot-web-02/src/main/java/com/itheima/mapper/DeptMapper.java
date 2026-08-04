package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    /**
     * 查询所有用户
     */

    /*
    @Result(column = "create_time", property = "createTime")
     */

    /*
    @Results({
        @Result(column = "create_time", property = "createTime"),
        @Result(column = "update_time", property = "updateTime")
     })

     @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc ")

     */

    @Select("select * from dept order by update_time desc ")
     List<Dept> getAllDept();

    @Delete("delete from  dept where id = #{id}")
    void deleteDeptById(Integer id);

    @Select("select * from dept where id = #{id}")
    Dept getDeptById(Integer id);

    // 新增/修改直接用 BaseMapper 的 insert / updateById，
    // create_time、update_time 由 MyMetaObjectHandler 自动填充
}
