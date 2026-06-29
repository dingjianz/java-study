package mavenstudy.springdemo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @RequestMapping("/request")
    public String request(HttpServletRequest request) {
        //1.获取请求方式
        String method = request.getMethod();
        System.out.println("请求方式:" + method);

        //2.获取请求URL
        String requestURL = request.getRequestURL().toString(); // http://localhost:8080/request
        System.out.println("请求URL:" + requestURL);

        String uri = request.getRequestURI(); // 资源访问路径 request
        System.out.println("请求URI:" + uri);

        //3.获取请求协议
        String protocol = request.getProtocol(); // HTTP/1.1
        System.out.println("请求协议:" + protocol);

        //4.获取请求参数，eg:name
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("请求参数name:" + name + ", age:" + age);

        // 5.获取请求参数 - eg: accept
        String accept = request.getHeader("accept");
        System.out.println("请求参数accept:" + accept);

        return "OK";
    }


}
