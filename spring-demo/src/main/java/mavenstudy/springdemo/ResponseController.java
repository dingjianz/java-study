package mavenstudy.springdemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ResponseController {
    // 响应码和响应头如果没有特殊要求，通常不手动设定，服务器会根据请求处理的逻辑，自动设置状态码和响应头。
    /**
     * 方式一： 通过HttpServletResponse对象返回响应数据
     */
    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        // 1.设置响应状态码
        response.setStatus(HttpServletResponse.SC_OK); // 200
        // response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY); // 301

        // 2.设置响应头
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setHeader("name", "jianding9");
        // response.setHeader("location", "/request"); // 重定向地址

        // 3.设置响应体
        response.getWriter().write("<h1>hello world</h1>");
    }

    /*
     方式二： 通过ResponseEntity对象返回响应数据
     */
    @RequestMapping("/response2")
    public ResponseEntity<String> response2() {
        return ResponseEntity.status(200)
                .header("name", "jianding9")
                .body("<h1>你好世界👋</h1>");
    }
}
