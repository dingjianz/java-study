package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("select * from emp order by update_time desc")
    List<Emp> getAllEmp();

    @Select("select * from emp where id = #{id}")
    Emp getEmpById(Integer id);

    @Update("update emp set name = #{name}, username = #{username}, gender = #{gender}, job = #{job}, entry_date = #{entryDate}, image = #{image}, phone = #{phone}, password = #{password}, update_time = #{updateTime} where id = #{id}")
    void updateEmp(Emp emp);

    @Delete("delete from emp where id = #{id}")
    void deleteEmpById(Integer id);
}
