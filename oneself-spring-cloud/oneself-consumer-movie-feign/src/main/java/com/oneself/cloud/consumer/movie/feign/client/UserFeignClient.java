package com.oneself.cloud.consumer.movie.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oneself.cloud.consumer.movie.feign.entity.UserInfo;

/**
 * 使用@FeignClient("microservice-provider-user")注解绑定microservice-provider-user服务，还可以使用url参数指定一个URL。
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月7日下午3:07:51
 */
@FeignClient(name = "oneself-provider-user")
public interface UserFeignClient {

	@RequestMapping("userinfo/simple/{username}")
	public UserInfo findByIdFeign(@RequestParam("username") String username);
}
