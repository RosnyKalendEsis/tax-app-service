package com.province.taxappservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaxAppServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxAppServiceApplication.class, args);
    }

}
