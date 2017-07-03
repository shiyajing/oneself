package com.oneself.cloud.consumer.movie.cloud.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.oneself.cloud.consumer.movie.cloud.feign.client.UserFeignClient;
import com.oneself.cloud.consumer.movie.cloud.feign.client.FeignClient2;
import com.oneself.cloud.consumer.movie.cloud.feign.model.ShUserInfoVO;

@RestController
public class FeignController {

	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private FeignClient2 feignClient2;


	@GetMapping("feign/{name}")
	public ShUserInfoVO findByIdFeign(@PathVariable String name) {
		ShUserInfoVO user = this.userFeignClient.findByIdFeign(name);
		return user;
	}

	@GetMapping("/{serviceName}")
	public String findServiceInfoFromEurekaByServiceName(@PathVariable String serviceName) {
		return this.feignClient2.findServiceInfoFromEurekaByServiceName(serviceName);
	}

}
