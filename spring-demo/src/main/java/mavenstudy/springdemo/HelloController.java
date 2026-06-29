package mavenstudy.springdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController: 创建一个RESTful Web服务 表是当前类是一个请求处理类
 */
@RestController
public class HelloController {

    /*
     * @RequestMapping: 创建处理请求的方法 标识请求路径
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    public String hello(String  name) {
        System.out.println("请求参数:" + name);
        return "Hello " + name + "~";
    }
}
