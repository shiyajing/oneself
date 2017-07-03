package org.oneself.consumer.movie.ribbon.without.eureka.controller;

import org.oneself.consumer.movie.ribbon.without.eureka.model.ShUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年6月20日下午4:48:46
 */
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

		ServiceInstance serviceInstance = this.loadBalancerClient.choose("oneself-provider-user");
		System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
				+ serviceInstance.getPort());

		return this.restTemplate.getForObject("http://oneself-provider-user/userinfo/simple/getUser/" + name, ShUserInfoVO.class);
	}

	@GetMapping("/test")
	public String test() {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("oneself-provider-user");
		System.out.println("111" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
				+ serviceInstance.getPort());

		/*ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("oneself-provider-user2");
		System.out.println("222" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":"
				+ serviceInstance2.getPort());
*/
		return "1";
	}
}
