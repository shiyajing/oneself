package com.oneself.cloud.consumer.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.oneself.cloud.consumer.movie.model.ShUserInfoVO;

/**
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月10日下午10:04:01
 */
@Service
public class RibbonService {

	@Autowired
	private RestTemplate restTemplate;
	
	public ShUserInfoVO queryUser(String name) {
	    // http://服务提供者的serviceId/url
	    return this.restTemplate.getForObject("http://oneself-provider-user/userinfo/simple/getUser/" + name, ShUserInfoVO.class);
	}
}
