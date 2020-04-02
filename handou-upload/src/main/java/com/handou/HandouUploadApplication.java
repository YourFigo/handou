package com.handou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Figo
 * @Date 2020/4/2 12:32
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HandouUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(HandouUploadApplication.class);
    }
}
