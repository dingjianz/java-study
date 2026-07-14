package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptController {
    @Resource
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    @GetMapping("/depts")
    public Result list(){
        return Result.success(deptService.getAllDept());
    }

    /**
     * 删除部门
     */

    @DeleteMapping("/depts")
    public Result delete(HttpServletRequest  request){
        /*
        方式一：通过原始的 HttpServletRequest对象获取参数(不推荐)
        且接收到的所有参数都是字符串类型 需求手动转换类型
         */
        Integer id = Integer.valueOf(request.getParameter("id"));
        deptService.deleteDeptById(id);
        return Result.success();
    }

    /*
        删除部门
        方式二：使用Spring 提供的 @RequestParam 注解获取参数
     */

    @DeleteMapping("/dept")
    public Result delete(@RequestParam("id") Integer id){
        deptService.deleteDeptById(id);
        return Result.success();
    }
}
