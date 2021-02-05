package suwaaaa.author.springcloud_config_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConfigController {

	@Value("${url}")
	private String urlString;
	
	@Autowired
	private Environment environment;
	
	@RequestMapping("/configtest/url")
	public String urStr() {
//		return "00000";
		
		return urlString;
	}
	
	@RequestMapping("/configtest/url2")
	public String method2() {
		return environment.getProperty("url");
	}
}
