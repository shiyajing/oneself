package com.oneself.cloud.consumer.movie.feign.with.hystrix.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 使用@EnableFeignClients开启Feign
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月7日下午4:25:42
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class MovieFeignHystrixApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MovieFeignHystrixApplication.class, args);
	}
}
