package com.hxb.eurekaclinet2;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * springCloud启动类
 *
 * @author Ma_wei
 * @since 2019-04-02 15:45
 */
@SpringCloudApplication
@EnableEurekaClient
public class EurekaClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient2Application.class, args);
    }
}
