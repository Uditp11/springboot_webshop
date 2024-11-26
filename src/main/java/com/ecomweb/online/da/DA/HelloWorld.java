package com.ecomweb.online.da.DA;

import org.springframework.web.bind.annotation.GetMapping;

public class HelloWorld {
    @GetMapping("/")
    public String funcHello(){
        return "Hello World!!";
    }
}
