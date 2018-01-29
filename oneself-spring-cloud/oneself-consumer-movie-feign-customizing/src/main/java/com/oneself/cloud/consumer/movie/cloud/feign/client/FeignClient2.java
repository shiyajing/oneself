package com.oneself.cloud.consumer.movie.cloud.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oneself.cloud.consumer.movie.config.Configuration2;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年6月26日上午9:28:05
 */
@FeignClient(name = "shiyajing", url = "http://localhost:8760/", configuration = Configuration2.class)
public interface FeignClient2 {

	@RequestMapping(value = "/eureka/apps/{serviceName}")
	public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName);
}
