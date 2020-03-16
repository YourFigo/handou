package cn.handou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Figo
 * @Date 2020/3/16 17:46
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HandouItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(HandouItemApplication.class);
    }
}
