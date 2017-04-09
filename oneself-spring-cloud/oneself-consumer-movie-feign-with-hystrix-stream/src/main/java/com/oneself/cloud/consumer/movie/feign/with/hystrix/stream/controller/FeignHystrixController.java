package com.oneself.cloud.consumer.movie.feign.with.hystrix.stream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oneself.cloud.consumer.movie.feign.with.hystrix.stream.entity.UserInfo;
import com.oneself.cloud.consumer.movie.feign.with.hystrix.stream.feign.UserFeignHystrixClient;

@RestController
public class FeignHystrixController {

	@Autowired
	private UserFeignHystrixClient userFeignHystrixClient;

	@GetMapping("feign/{name}")
	public UserInfo findByIdFeign(@PathVariable String name) {
		UserInfo user = this.userFeignHystrixClient.findByIdFeign(name);
		return user;
	}

}
