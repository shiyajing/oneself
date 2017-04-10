package com.oneself.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这边的@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新
 * 
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月10日下午11:24:20
 */
@RestController
@RefreshScope
public class ConfigClientController {
	
	@Value("${spring.cloud.config.profile}")
	private String profile;

	@GetMapping("/hello")
	public String hello() {
		return this.profile;
	}
}
