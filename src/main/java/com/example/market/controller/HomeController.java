package com.example.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(value = "tt", method = RequestMethod.GET)
    public String dataCollect() {
        return "dataCollect";
    }
    @RequestMapping("dataDisplay")
    public String dataDisplay() {

        return "dataDisplay";
    }

    @RequestMapping(value = "home")
    public String home() {
        return "home";
    }

    @PostMapping("test")
    @ResponseBody
    public String test() {
        return "hello";
    }

}
