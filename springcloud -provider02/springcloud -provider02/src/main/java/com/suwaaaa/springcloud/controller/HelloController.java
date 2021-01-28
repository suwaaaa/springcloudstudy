package com.suwaaaa.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/service/hello")
	public String helloString() {
		
		return "Hello, Spring Cloud. provider 002 ";
	}
}
