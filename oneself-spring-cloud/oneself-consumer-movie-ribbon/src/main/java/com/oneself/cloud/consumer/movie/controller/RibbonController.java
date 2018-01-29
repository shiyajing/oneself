package com.oneself.cloud.consumer.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.oneself.cloud.consumer.movie.model.SgSysStaffVO;
import com.oneself.cloud.consumer.movie.service.RibbonService;

@RestController
public class RibbonController {

	@Autowired
	private RibbonService ribbonService;

	@GetMapping("/ribbon/{name}")
	public SgSysStaffVO findById(@PathVariable String name) {
		return this.ribbonService.queryUser(name);
	}
	
}
