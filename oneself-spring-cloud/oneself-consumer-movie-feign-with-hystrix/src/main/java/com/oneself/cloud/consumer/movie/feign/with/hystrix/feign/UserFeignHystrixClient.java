package com.oneself.cloud.consumer.movie.feign.with.hystrix.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oneself.cloud.consumer.movie.feign.with.hystrix.feign.UserFeignHystrixClient.HystrixClientFallback;
import com.oneself.cloud.consumer.movie.feign.with.hystrix.model.ShUserInfoVO;

/**
 * 使用@FeignClient注解的fallback属性，指定fallback类
 * 
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月7日下午3:48:12
 */
@FeignClient(name = "oneself-provider-user", fallback = HystrixClientFallback.class)
public interface UserFeignHystrixClient {

	@RequestMapping("userinfo/simple/getUser/{username}")
	public ShUserInfoVO findByIdFeign(@RequestParam("username") String username);

	/**
	 * 这边采取了和Spring
	 * Cloud官方文档相同的做法，将fallback类作为内部类放入Feign的接口中，当然也可以单独写一个fallback类。
	 * 
	 * @author shiyajing
	 * @E-mail 345129564@qq.com
	 * @version 2017年4月7日下午3:49:44
	 */
	@Component
	static class HystrixClientFallback implements UserFeignHystrixClient {
		private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

		/**
		 * hystrix fallback方法
		 * 
		 * @param id
		 * @return
		 */
		@Override
		public ShUserInfoVO findByIdFeign(String username) {
			HystrixClientFallback.LOGGER.info("异常发生，进入fallback方法，接收的参数：username = {}", username);
			ShUserInfoVO user = new ShUserInfoVO();
			user.setUserId("-1L");
			user.setUserName("default username");
			user.setUserPassword("123456");
			return user;
		}

	}
}
