package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

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

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void addDept(Dept dept);

    @Select("select * from dept where id = #{id}")
    Dept getDeptById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void updateDept(Dept dept);
}
