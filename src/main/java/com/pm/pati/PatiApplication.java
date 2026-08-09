package com.pm.pati;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PatiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatiApplication.class, args);
    }
}
