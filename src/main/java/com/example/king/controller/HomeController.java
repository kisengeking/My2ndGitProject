package com.example.king;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "✅ Spring Boot app is running successfully!";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
