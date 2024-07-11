package com.duyhk.apibanhang;

import com.duyhk.apibanhang.service.MauSacService;
import com.duyhk.apibanhang.service.iplm.MauSacServiceIplm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApiBanHangApplication {

    public static void main(String[] args) {
         SpringApplication.run(ApiBanHangApplication.class, args);
    }
}
