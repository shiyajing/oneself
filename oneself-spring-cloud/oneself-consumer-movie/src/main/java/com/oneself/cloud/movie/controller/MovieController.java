package com.oneself.cloud.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oneself.cloud.movie.model.ShUserInfoVO;

@RestController
public class MovieController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${user.userServicePath}")
	private String userServicePath;

	@GetMapping("/movie/{name}")
	public ShUserInfoVO findById(@PathVariable String name) {
		return this.restTemplate.getForObject(this.userServicePath + name, ShUserInfoVO.class);
	}

}
