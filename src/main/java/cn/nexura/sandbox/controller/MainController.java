package cn.nexura.sandbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PeiYP
 * @since 2024年01月02日 14:08
 */
@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }

}
