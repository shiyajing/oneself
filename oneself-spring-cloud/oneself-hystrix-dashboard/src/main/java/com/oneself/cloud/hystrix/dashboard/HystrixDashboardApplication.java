package com.oneself.cloud.hystrix.dashboard;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 测试步骤: 1. 访问http://localhost:8030/hystrix.stream 可以查看Dashboard 2. 在上面的输入框填入:
 * http://想监控的服务:端口/hystrix.stream进行测试 注意：首先要先调用一下想监控的服务的API，否则将会显示一个空的图表.
 * 
 * @author shiyajing
 * @E-mail 345129564@qq.com
 * @version 2017年4月7日下午4:09:22
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(HystrixDashboardApplication.class).web(true).run(args);
	}

}
