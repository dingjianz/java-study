package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    /*@DeleteMapping("/depts")
    public Result delete(HttpServletRequest  request){
        return deptService.delete(request.getParameter("id"));
    }*/
}
