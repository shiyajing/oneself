package com.oneself.cloud.consumer.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年6月26日上午9:26:10
 */
@Configuration
public class Configuration2 {

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("user", "password123");
	}

}
