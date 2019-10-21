package com.nc.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class TestController {

    /**
     *
     * @return string to be displayed
     */
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
