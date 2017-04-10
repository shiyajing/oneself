package com.oneself.cloud.consumer.movie.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oneself.cloud.consumer.movie.feign.client.UserFeignClient;
import com.oneself.cloud.consumer.movie.feign.model.ShUserInfoVO;

@RestController
public class FeignController {

	@Autowired
	private UserFeignClient userFeignClient;

	@GetMapping("feign/{name}")
	public ShUserInfoVO findByIdFeign(@PathVariable String name) {
		ShUserInfoVO user = this.userFeignClient.findByIdFeign(name);
		return user;
	}
}
