package cn.handou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author Figo
 * @Date 2020/3/16 12:06
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class HandouGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(HandouGatewayApplication.class);
    }
}
