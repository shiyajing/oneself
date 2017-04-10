package com.oneself.cloud.config.client.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月11日上午12:16:07
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientEurekaApplication.class, args);
	}
}
