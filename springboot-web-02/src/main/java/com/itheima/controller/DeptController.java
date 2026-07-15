package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
        注意事项：一旦声明了@RequestParam，那么该参数在请求时必须传递，否则会报错（默认 required 为 true）
        如果接收的参数 例如: /depts?id=1 与方法形参变量名相同，直接定义方法形参即可接收参数，省略@RequestParam注解
        eg: public void delete(Integer id) -- 实际开发中推荐使用
     */

    @DeleteMapping("/dept")
    public Result delete(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id){
        deptService.deleteDeptById(id);
        return Result.success();
    }

    /*
    接收json格式的请求参数: POST
    JSON格式的参数，通常会使用一个实体对象进行接收
    规则：JSON数据的键名与方法形参对象的属性名相同，并需要使用 @RequestBody 注解进行接收
     */

    @PostMapping("/depts")
    public Result addDept(@RequestBody @Valid Dept dept){
        deptService.addDept(dept);
        return Result.success();
    }

    /*
    接收请求参数（路径参数）:GET /depts/1
    路径参数：通过请求URL直接传递参数，使用{...}来标识该路径参数，需要使用@pathVariable注解进行接收
    如果路径参数与方法形参变量名相同，直接写 @pathVariable注解即可，不需要给 @pathVariable注解变量名
    eg: /depts/{id} -> public void getDeptById(@PathVariable Integer id)

    url携带多个参数： GET /depts/{id}/{name} -> public void getDeptById(@PathVariable Integer id, @PathVariable String name)
     */
    @GetMapping("/depts/{id}")
    public Result getDeptById(@PathVariable("id") Integer id){
        return Result.success(deptService.getDeptById(id));
    }

    @PutMapping("/depts")
    public Result updateDept(@RequestBody @Valid Dept dept){
        deptService.updateDept(dept);
        return Result.success();
    }
}
