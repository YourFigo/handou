package com.handou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author Figo
 * @Date 2020/3/16 17:46
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.handou.item.mapper")
public class HandouItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(HandouItemApplication.class);
    }
}
