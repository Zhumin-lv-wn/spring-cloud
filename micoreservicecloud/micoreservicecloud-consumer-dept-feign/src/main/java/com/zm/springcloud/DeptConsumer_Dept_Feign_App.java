package com.zm.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@ComponentScan("com.zm.springcloud")
public class DeptConsumer_Dept_Feign_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_Dept_Feign_App.class,args);
    }
}
