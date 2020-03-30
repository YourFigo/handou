package com.handou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author Figo
 * @Date 2020/3/16 11:37
 */
@SpringBootApplication
@EnableEurekaServer
public class HandouRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(HandouRegistryApplication.class);
    }
}
