package com.suwaaaa.springcloud.HystrixConfig;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;



public class MyHystrixCommand extends HystrixCommand<String>{

	private RestTemplate restTemplate;
	
	public MyHystrixCommand(Setter setter,RestTemplate restTemplate) {
		super(setter);
		this.restTemplate= restTemplate;
		// TODO Auto-generated constructor stub
	}


	@Override  //    调用远程服务
	protected String run() throws Exception {
		// TODO Auto-generated method stub
		return restTemplate
				.getForEntity("http://SPRING-CLOUD-EUREKA-PROVIDER00/service/hello", String.class).getBody();
	}

	
	/**
	  *  Class Name: MyHystrixCommand.java
	  *  Description:
	  *  
	  *  自定义请求服务熔断    如果服务超时，异常，不可用则触发这个方法
	  *  
	  *  @author  suwaaaa  DateTime 2021年1月31日 上午2:55:48
	  *  @email  1264584869@qq.com 
	  *  @version 1.0
	  */
	@Override
	public String getFallback() {
		
		Throwable throwable = getExecutionException();
		System.out.println(throwable.getMessage() + "++++++++++" + throwable.getStackTrace());
		return "自定义请求服务熔断    如果服务超时，异常，不可用则触发这个方法++++++可以实现服务熔断和服务降级";
	}
	
}
