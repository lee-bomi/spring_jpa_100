package com.zerobase.springjpa100.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondController {

    @GetMapping("/hello-spring")
    public String helloString() { //rest형태의 주소사용

        return "hello spring";
    }

    @GetMapping("/hello-rest")
    public String helloRest() { //rest형식의 어노테이션 사용

        return "hello rest";
    }

    @GetMapping("/api/helloworld")
    public String helloRestApi() { //rest형식의 함수 작성

        return "hello rest api";
    }


}
