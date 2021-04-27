package com.galdovich.esm.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.galdovich.esm"})
@EntityScan(basePackages = {"com.galdovich.esm.entity"})
public class GiftSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiftSystemApplication.class, args);
    }
}
