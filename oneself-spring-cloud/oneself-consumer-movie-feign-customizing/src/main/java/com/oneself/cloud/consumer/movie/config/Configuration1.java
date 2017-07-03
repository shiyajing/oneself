package com.oneself.cloud.consumer.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年6月26日上午9:25:33
 */
@Configuration
public class Configuration1 {

	@Bean
	public Contract feignContract() {
		return new feign.Contract.Default();
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

}
