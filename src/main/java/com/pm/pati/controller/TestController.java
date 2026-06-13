package com.pm.pati.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "testing this by me";
    }

    @GetMapping("/heartBeat")
    public String heartBeat(){
        return "heat beat is okay!";
    }
}
