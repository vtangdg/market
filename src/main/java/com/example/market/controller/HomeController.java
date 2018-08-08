package com.example.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = "home")
    public String home() {
        return "home";
    }

    @GetMapping("test")
    public String test() {
        return "common/nav";
    }

}
