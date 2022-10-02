package com.shopjr.market;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/market")
public class Prueba {
    @GetMapping(value = "/hello")
    public String sayHello() {
        return "Step by Step";
    }
}
