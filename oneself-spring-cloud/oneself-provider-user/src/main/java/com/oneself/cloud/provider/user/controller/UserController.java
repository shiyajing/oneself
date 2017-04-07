package com.oneself.cloud.provider.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.oneself.cloud.provider.user.entity.UserInfo;

@RestController
@RequestMapping(value = "/userinfo", produces = "application/json;charset=UTF-8")
public class UserController {

	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * 注：@GetMapping("/{username}")是spring 4.3的新注解等价于：
	 * 
	 * @RequestMapping(value = "/username", method = RequestMethod.GET)
	 *                       类似的注解还有@PostMapping等等
	 * @param username
	 * @return user信息
	 */
	@GetMapping("/simple/{username}")
	public @ResponseBody UserInfo findById(@PathVariable String username) {
		UserInfo user = new UserInfo();
		user.setUserId(UUID.randomUUID().toString().replace("-", ""));
		user.setUserName(username);
		user.setUserPassword("123456");
		return user;
	}

	/**
	 * 本地服务实例的信息
	 * 
	 * @return
	 */
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}
}
