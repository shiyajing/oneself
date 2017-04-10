package com.oneself.cloud.consumer.movie.ribbon.with.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oneself.cloud.consumer.movie.ribbon.with.hystrix.model.ShUserInfoVO;
import com.oneself.cloud.consumer.movie.ribbon.with.hystrix.service.RibbonHystrixService;

@RestController
public class RibbonHystrixController {

	@Autowired
	private RibbonHystrixService ribbonHystrixService;

	@GetMapping("/ribbon/{name}")
	public ShUserInfoVO findById(@PathVariable String name) {
		return this.ribbonHystrixService.findById(name);
	}
}
