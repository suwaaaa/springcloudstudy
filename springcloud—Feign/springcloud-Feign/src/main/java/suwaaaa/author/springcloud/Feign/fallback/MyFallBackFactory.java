package suwaaaa.author.springcloud.Feign.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;

import suwaaaa.author.springcloud.Feign.service.HelloService;


/**
  *  Class Name: MyFallBackFactory.java
  *  Description:
  *  
  *  服务熔断获取异常信息，拿到远程服务的异常信息
  *  
  *  @author  suwaaaa  DateTime 2021年2月5日 上午12:58:01
  *  @email  1264584869@qq.com 
  *  @version 1.0
  */
public class MyFallBackFactory implements FallbackFactory<HelloService>{

	
	@Override
	public HelloService create(Throwable cause) {
		// TODO Auto-generated method stub
		
		
		return new HelloService() {
			
			@Override
			public String helloString() {
				// TODO Auto-generated method stub
				return cause.getMessage();
			}
		};
	}

}
