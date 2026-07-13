package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
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
}
