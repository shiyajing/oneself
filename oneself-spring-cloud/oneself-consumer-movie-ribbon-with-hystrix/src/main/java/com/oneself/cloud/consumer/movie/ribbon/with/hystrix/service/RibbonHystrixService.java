package com.oneself.cloud.consumer.movie.ribbon.with.hystrix.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oneself.cloud.consumer.movie.ribbon.with.hystrix.entity.UserInfo;

@Service
public class RibbonHystrixService {

	@Autowired
	private RestTemplate restTemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(RibbonHystrixService.class);

	/**
	 * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
	 * 
	 * @param id
	 *            id
	 * @return 通过id查询到的用户
	 */
	@HystrixCommand(fallbackMethod = "fallback")
	public UserInfo findById(String name) {
		return this.restTemplate.getForObject("http://oneself-provider-user/userinfo/simple/" + name, UserInfo.class);
	}
	
	/**
	   * hystrix fallback方法
	   * @param id id
	   * @return 默认的用户
	   */
	  public UserInfo fallback(String name) {
	    RibbonHystrixService.LOGGER.info("异常发生，进入fallback方法，接收的参数：name = {}", name);
	    UserInfo user = new UserInfo();
	    user.setUserId("-1L");
	    user.setUserName("default username");
	    user.setUserPassword("123456");
	    return user;
	  }
}
