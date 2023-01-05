package com.zerobase.springjpa100.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping("/first-url")
    public void firstMethod() {

    }

    @ResponseBody
    @GetMapping("/hello-world")
    public String hello() {

        return "hello World";
    }

}
