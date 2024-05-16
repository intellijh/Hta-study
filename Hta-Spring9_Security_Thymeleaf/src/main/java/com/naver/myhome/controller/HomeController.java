package com.naver.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "redirect:/member/login";
    }

    @GetMapping("/error/403")
    public String error_403() {
        return "error/403";
    }
}