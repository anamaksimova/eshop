package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    @GetMapping("/access__denied")
    public String accessDenied() {
        return "access__denied";
    }
}