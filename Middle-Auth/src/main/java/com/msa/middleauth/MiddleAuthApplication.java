package com.msa.middleauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MiddleAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiddleAuthApplication.class, args);
    }

}
