package suwaaaa.author.springcloud.Feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import suwaaaa.author.springcloud.Feign.fallback.MyFallBack;
import suwaaaa.author.springcloud.Feign.fallback.MyFallBackFactory;

/**
  *  Class Name: HelloService.java
  *  Description:
  *  
  *  使用Feign的客服端注解绑定远程的服务              该远程服务名字可以是大写也可以是小写 
  *  
  *  @author  suwaaaa  DateTime 2021年1月31日 下午11:21:13
  *  @email  1264584869@qq.com 
  *  @version 1.0
  */


@FeignClient(name = "SPRING-CLOUD-EUREKA-PROVIDER",/*fallback=MyFallBack.class*/fallbackFactory = MyFallBackFactory.class)  // 使用Feign实现服务熔断
public interface HelloService {
	
	/**MyFallBackFactory.java 
	  *  服务熔断获取异常信息，拿到远程服务的异常信息
	  *  
	  *  
	  *  Description:
	  *  -
	  *  声明远程服务者提供的方法  比如provider01的helloString方法
	  *  声明的方法路径需要是一样的
	  *  进而实现该接口
	  *  @author  suwaaaa  DateTime 2021年2月4日 下午3:53:57
	  *  @email  1264584869@qq.com 
	  *  @version 1.0
	  */
	@RequestMapping("/service/hello")
	public String helloString();
	
	
}
