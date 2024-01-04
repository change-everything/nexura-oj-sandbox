package cn.nexura.sandbox.controller;

import cn.nexura.sandbox.JavaDockerCodeSandbox;
import cn.nexura.sandbox.model.ExecuteCodeRequest;
import cn.nexura.sandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author PeiYP
 * @since 2024年01月02日 14:08
 */
@RestController
@RequestMapping("/")
public class MainController {

    // 定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

    @Resource
    private JavaDockerCodeSandbox dockerCodeSandbox;


    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }


    @PostMapping("/executeCode")
    public ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest, HttpServletResponse response, HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (authHeader != null && !authHeader.equals(AUTH_REQUEST_SECRET)) {
            response.setStatus(403);
            return null;
        }
        if (executeCodeRequest == null) {
            throw new RuntimeException("请求参数为空");
        }
        return dockerCodeSandbox.doExecute(executeCodeRequest);
    }

}
