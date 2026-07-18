package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     */
    @Select("select count(*) from emp e join dept d on e.dept_id = d.id")
    Long count();

    /**
     * 分页查询（start 为起始偏移量，pageSize 为每页条数）
     */
    @Select("select e.* , d.name deptName from emp e join dept d on e.dept_id = d.id "
            + "order by e.update_time desc limit #{start},#{pageSize}")
    List<Emp> getAllEmp(Integer start, Integer pageSize);

    @Select("select * from emp e where id = #{id}")
    Emp getEmpById(Integer id);

    @Update("update emp set name = #{name}, username = #{username}, gender = #{gender}, job = #{job}, entry_date = #{entryDate}, image = #{image}, phone = #{phone}, password = #{password}, update_time = #{updateTime} where id = #{id}")
    void updateEmp(Emp emp);

    @Delete("delete from emp where id = #{id}")
    void deleteEmpById(Integer id);
}
