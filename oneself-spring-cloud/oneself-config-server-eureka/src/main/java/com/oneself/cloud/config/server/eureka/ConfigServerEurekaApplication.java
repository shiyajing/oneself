package com.oneself.cloud.config.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月11日上午12:09:58
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerEurekaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerEurekaApplication.class, args);
	}
}
