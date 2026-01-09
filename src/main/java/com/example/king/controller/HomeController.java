package com.example.king.controller;
import org.springframework.web.bind.annotation.*;


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
