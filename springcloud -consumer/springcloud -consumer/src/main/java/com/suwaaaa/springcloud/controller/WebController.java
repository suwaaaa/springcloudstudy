package com.suwaaaa.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebController {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping("/web/hello")
	public String hellString() {
		
		//String connectionString = restTemplate.getForEntity("http://localhost:7777/service/hello", String.class).getBody();
		
		
		//  由于加入了LoadBalance注解，可以注解只用服务名称
		String servicenameString = restTemplate
				.getForEntity("http://SPRING-CLOUD-EUREKA-PROVIDER00/service/hello", String.class)
				.getBody();
				
				
		
		return "调用远程springcloud的远程服务"  + servicenameString;
	}
}
