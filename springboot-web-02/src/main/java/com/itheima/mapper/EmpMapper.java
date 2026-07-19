package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
    /**
     * 查询总记录数
     */
    @Select("select count(*) from emp e join dept d on e.dept_id = d.id")
    Long count();

    /**
     * 分页查询（支持条件查询）
     */
   /* IPage<Emp> getAllEmp(IPage<Emp> page,
                        @Param("name") String name,
                        @Param("gender") Integer gender,
                        @Param("begin") LocalDate begin,
                        @Param("end") LocalDate end);*/
    IPage<Emp> getAllEmp(IPage<Emp> page, @Param("query") EmpQueryParam query);

    Emp getEmpById(Integer id);

    @Update("update emp set name = #{name}, username = #{username}, gender = #{gender}, job = #{job}, salary = #{salary}, dept_id = #{deptId}, entry_date = #{entryDate}, image = #{image}, phone = #{phone}, update_time = #{updateTime} where id = #{id}")
    void updateEmp(Emp emp);

    @Delete("delete from emp where id = #{id}")
    void deleteEmpById(Integer id);

    void insertEmp(Emp emp);
}
