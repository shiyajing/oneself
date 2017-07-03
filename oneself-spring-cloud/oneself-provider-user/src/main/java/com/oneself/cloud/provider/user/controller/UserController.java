package com.oneself.cloud.provider.user.controller;

import java.util.List;
import java.util.UUID;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.oneself.cloud.provider.user.model.ShUserInfoVO;
import com.oneself.cloud.provider.user.service.IUserInfoService;

@RestController
@RequestMapping(value = "/userinfo", produces = "application/json;charset=UTF-8")
public class UserController {

	@Autowired
	IUserInfoService service;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private EurekaClient eurekaClient;

	/**
	 * 注：@GetMapping("/{username}")是spring 4.3的新注解等价于：
	 * 
	 * @RequestMapping(value = "/username", method = RequestMethod.GET)
	 *                       类似的注解还有@PostMapping等等
	 * @param username
	 * @return user信息
	 */
	@GetMapping("/simple/getUser/{username}")
	public @ResponseBody ShUserInfoVO findById(@PathVariable String username) {
		List<ShUserInfoVO> user = service.queryUser(username);
		return user.get(0);
	}

	@PostMapping("/simple/register")
	public @ResponseBody ShUserInfoVO register(@RequestBody String json) {
		ShUserInfoVO user = JSONObject.parseObject(json, ShUserInfoVO.class, Feature.AllowSingleQuotes);
		user.setUserId(UUID.randomUUID().toString().replace("-", ""));
		ShUserInfoVO vo = service.saveUser(user);
		return vo;
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

	@GetMapping("/eureka-instance")
	public String serviceUrl() {
		InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("oneself-provider-user", false);
		return instance.getHomePageUrl();
	}

}
