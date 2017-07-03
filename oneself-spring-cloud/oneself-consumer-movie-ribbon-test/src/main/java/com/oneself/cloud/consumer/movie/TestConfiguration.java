package com.oneself.cloud.consumer.movie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年6月20日下午10:19:04
 */
@Configuration
@ExcludeFromComponentScan
public class TestConfiguration {

	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}

}
