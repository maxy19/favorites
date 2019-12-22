package com.mxy.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ApplyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApplyApplication.class, args);
    }
}
