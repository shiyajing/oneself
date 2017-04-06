package com.oneself.cloud.provider.user.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.oneself.cloud.provider.user.entity.UserInfo;

@RestController
@RequestMapping(value = "/userinfo", produces = "application/json;charset=UTF-8")
public class UserController {

	@GetMapping("/simple/{username}")
	public @ResponseBody UserInfo findById(@PathVariable String username) {
		UserInfo user=new UserInfo();
		user.setUserId(UUID.randomUUID().toString().replace("-", ""));
		user.setUserName(username);
		user.setUserPassword("123456");
		return user;
	}
	
}
