package suwaaaa.author.springcloud.Feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import suwaaaa.author.springcloud.Feign.service.HelloService;

@RestController
public class FeignController {

	@Autowired
	private HelloService helloService;
	
	
	@RequestMapping("/feign/hello")
	public String hellotest() {
		
		//  调用声明式的方法
		return helloService.helloString();
	}
}
