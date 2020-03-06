package com.wws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class WsTestController {
    @RequestMapping(value="/sayHello")
    public String sayHello(){
        return "hello";
    }
}
