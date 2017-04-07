package com.oneself.cloud.consumer.movie.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oneself.cloud.consumer.movie.ribbon.entity.UserInfo;

@RestController
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private LoadBalancerClient loadBalancerClient;

  @GetMapping("/ribbon/{username}")
  public UserInfo findById(@PathVariable String username) {
	  // http://服务提供者的serviceId/url
    return this.restTemplate.getForObject("http://oneself-provider-user/userinfo/simple/" + username, UserInfo.class);
  }

  @GetMapping("/test")
  public String test() {
    ServiceInstance serviceInstance = this.loadBalancerClient.choose("oneself-provider-user");
    System.out.println("111" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

    ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("microservice-provider-user2");
    System.out.println("222" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());

    return "1";
  }
}
