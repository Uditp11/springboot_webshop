package com.ecomweb.online.da.DA;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/hello")
    public String funcHello(){
        return "Hello World!!";
    }
}
