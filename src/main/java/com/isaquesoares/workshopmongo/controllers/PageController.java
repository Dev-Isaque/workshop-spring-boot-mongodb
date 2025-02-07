package com.isaquesoares.workshopmongo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/login")
    public String showLoginPageAgain() {
        return "login";
    }

    @GetMapping("/esqueci-senha")
    public String showEsqueciSenhaPage() {
        return "esqueci-senha";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

}
