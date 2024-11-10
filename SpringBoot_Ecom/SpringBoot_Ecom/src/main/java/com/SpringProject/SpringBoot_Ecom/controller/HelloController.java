package com.SpringProject.SpringBoot_Ecom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/home")
    public String hello(){
        return "hello";
    }

}
