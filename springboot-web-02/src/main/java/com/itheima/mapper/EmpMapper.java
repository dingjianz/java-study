package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

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

    @Delete("delete from emp where id = #{id}")
    void deleteEmpById(Integer id);

    // 新增/修改直接用 BaseMapper 的 insert / updateById，
    // 这样 create_time、update_time 才会被 MyMetaObjectHandler 自动填充；
    // insert 也会把自增主键回填到实体的 id 上
}
