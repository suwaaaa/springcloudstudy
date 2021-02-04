package suwaaaa.author.springcloud.Feign.fallback;

import org.springframework.stereotype.Component;

import suwaaaa.author.springcloud.Feign.service.HelloService;

@Component
public class MyFallBack implements HelloService{

	
	@Override
	public String helloString() {
		// TODO Auto-generated method stub
		return "远程服务不可用时，暂时采用本地逻辑代替。。。";
	}

	
}
