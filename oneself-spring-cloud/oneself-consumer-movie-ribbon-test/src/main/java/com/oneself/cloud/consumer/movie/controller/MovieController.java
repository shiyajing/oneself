package com.oneself.cloud.consumer.movie.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oneself.cloud.consumer.movie.model.ShUserInfoVO;

@RestController
public class MovieController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/movie/{name}")
	public ShUserInfoVO findById(@PathVariable String name) {
		// http://localhost:7900/simple/
		// VIP virtual IP
		// HAProxy Heartbeat
		return this.restTemplate.getForObject("http://oneself-provider-user/userinfo/simple/getUser/" + name,
				ShUserInfoVO.class);
	}
	
	  @GetMapping("/test")
	  public String test() {
	    ServiceInstance serviceInstance = this.loadBalancerClient.choose("oneself-provider-user");
	    System.out.println("111" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

	    ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("oneself-provider-user2");
	    System.out.println("222" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());

	    return "1";
	  }

	  @GetMapping("/list-all")
	  public List<ShUserInfoVO> listAll() {
	    // wrong
	    //    List<User> list = this.restTemplate.getForObject("http://microservice-provider-user/list-all", List.class);
	    //    for (User user : list) {
	    //      System.out.println(user.getId());
	    //    }

	    // right
		  ShUserInfoVO[] users = this.restTemplate.getForObject("http://oneself-provider-user/list-all", ShUserInfoVO[].class);
	    List<ShUserInfoVO> list2 = Arrays.asList(users);
	    for (ShUserInfoVO user : list2) {
	      System.out.println(user.getUserId());
	    }

	    return list2;
	  }

}
