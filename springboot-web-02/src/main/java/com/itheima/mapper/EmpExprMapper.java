package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpExprMapper extends BaseMapper<EmpExpr> {
    /**
     * 批量插入员工工作经历
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工 id 查询其工作经历列表（供 Emp 关联查询的 collection 使用）
     */
    @Select("select * from emp_expr where emp_id = #{empId}")
    List<EmpExpr> getByEmpId(Integer empId);

    /**
     * 根据员工 id 删除其全部工作经历（更新员工时先删后插）
     */
    @Delete("delete from emp_expr where emp_id = #{empId}")
    void deleteByEmpId(Integer empId);
}
