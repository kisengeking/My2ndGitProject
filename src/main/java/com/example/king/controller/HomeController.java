package com.example.king;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/check")
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
