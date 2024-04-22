package com.example.reggie_takeout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ReggieTakeoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReggieTakeoutApplication.class, args);
    }

}
