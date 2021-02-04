package suwaaaa.author.spring_cloud_config_server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Value("${url}")
	private String urlString;
	
	@RequestMapping("/configtest/url")
	public String urStr() {
		
		return urlString;
	}
}
