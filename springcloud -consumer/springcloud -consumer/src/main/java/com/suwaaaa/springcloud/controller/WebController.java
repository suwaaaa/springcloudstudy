package com.suwaaaa.springcloud.controller;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.suwaaaa.springcloud.HystrixConfig.MyHystrixCommand;
import com.suwaaaa.springcloud.damain.User;

@RestController
public class WebController {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping("/web/hello")
	public String hellString() {
		
		//String connectionString = restTemplate.getForEntity("http://localhost:7777/service/hello", String.class).getBody();
		
		
		//  由于加入了LoadBalance注解，可以注解只用服务名称
//		String servicenameString = restTemplate
//				.getForEntity("http://SPRING-CLOUD-EUREKA-PROVIDER00/service/hello", String.class)
//				.getBody();
				
		
		//   string------ResponseEntity<user>------ResponseEntity<map>
		ResponseEntity<String>  responseEntity = restTemplate
				.getForEntity("http://SPRING-CLOUD-EUREKA-PROVIDER/service/hello", String.class);
		int getStatusCodeValue = responseEntity.getStatusCodeValue();
		HttpStatus astatus =   responseEntity.getStatusCode();
		HttpHeaders httpheader = responseEntity.getHeaders();
		String getbodyString = responseEntity.getBody();
		
		System.out.println(getStatusCodeValue);
		System.out.println(astatus);
		System.out.println(httpheader+getbodyString);
		return "调用远程springcloud的远程服务"  + responseEntity;
	}
	
	User user = new User();
	
	@RequestMapping("/web/setUser/{name}")
	public User setUser() {
		ResponseEntity<User>  responseEntity = restTemplate
				.getForEntity("http://SPRING-CLOUD-EUREKA-PROVIDER/service/setUser", User.class);   //      	//    ?id={id}&name={name}&phone={phone}
		int getStatusCodeValue = responseEntity.getStatusCodeValue();
		HttpStatus astatus =   responseEntity.getStatusCode();
		HttpHeaders httpheader = responseEntity.getHeaders();
		User getbodyString = responseEntity.getBody();
		
		System.out.println(getStatusCodeValue);
		System.out.println(astatus);
		System.out.println(httpheader);
		System.out.println(getbodyString);
		
		return user ; 
	}
	
	
	
	

	MultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<String, Object>();
	
	@RequestMapping("/web/postUser")
	public User postUser() {
		
		dataMap.add("name", "彭雯妮");
		dataMap.add("address", "上海恭王府");
		dataMap.add("phone", 44545669);
		
		User  userdata = restTemplate
				.postForObject("http://SPRING-CLOUD-EUREKA-PROVIDER/service/postUser", dataMap,User.class );

		System.out.println(userdata.getName()+userdata.getAddress());
		
		return restTemplate
				.postForObject("http://SPRING-CLOUD-EUREKA-PROVIDER/service/postUser", dataMap,User.class ); 
	}																																								
	
	
	@RequestMapping("/web/putUser")
	public String putUser() {
		
		dataMap.add("name", "安吉为");
		dataMap.add("address", "江梅");
		dataMap.add("phone", 64846449);
		
		restTemplate.put("http://SPRING-CLOUD-EUREKA-PROVIDER/service/putUser",dataMap);
	
		return "success  ---  /web/putUser  ---" ; 
	}
	

	@RequestMapping("/web/deleteUser")
	public String deleteUser() {
		
		Map< String, Object> paramMap = new ConcurrentHashMap<>();
		paramMap.put("name","将卫军");
		paramMap.put("address", "进景区");
		paramMap.put("phone",	 45646);
		
		
		restTemplate.delete("http://SPRING-CLOUD-EUREKA-PROVIDER/service/deleteUser?"
														+ "name={name}&address={address}&phone={phone}",paramMap);
	
		return "success  ---/web/deleteUser---  " ; 
	}

	
	
	
	
	
	
	@RequestMapping("/web/hystrix")
	@HystrixCommand (fallbackMethod = "feedback",commandProperties = 
										{@HystrixProperty(name = "execution.ioslation.thread.timeoutInMillisecond",value="1500")})  //,ignoreExceptions = Exception.class
	//			设置超时的时间为1.5秒，默认为1000毫秒，即1秒																											忽略抛出的异常
	
	public String hystrix() {
		
		ResponseEntity<String>  responseEntity = restTemplate
				.getForEntity("http://SPRING-CLOUD-EUREKA-PROVIDER/service/hello", String.class);
		 
		int getStatusCodeValue = responseEntity.getStatusCodeValue();
		HttpStatus astatus =   responseEntity.getStatusCode();
		HttpHeaders httpheader = responseEntity.getHeaders();
		String getbodyString = responseEntity.getBody();
		System.out.println(getStatusCodeValue);
		System.out.println(astatus);
		System.out.println(httpheader+getbodyString);
		return "调用远程springcloud的远程服务"  + responseEntity+"测试Hystrix服务熔断";
	}
	
	public String feedback(Throwable throwable) {
		//      融断的回调方法，也就是降级方法
		System.out.println("异常：===="+throwable.getMessage());
		return "error"+"+++使用Hystrix服务熔断+++";	
	} 
	
	
	
	

	
	
	/**
	  *  Description:
	  * 自定义请求服务熔断    如果服务超时，异常，不可用则触发这个方法
	  * 
	  *  @author  suwaaaa  DateTime 2021年1月31日 上午2:59:40
	  *  @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	  */
	@RequestMapping("/web/hystrixcommand")
	public String hystrixcommand() throws InterruptedException, ExecutionException {
		
		MyHystrixCommand myHystrixCommand = new MyHystrixCommand(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate);
		
		
		//		同步服务调用，该方法执行后，会等待结果，拿到了远程的返回结果，该方法才返回
//		String mystring = myHystrixCommand.execute();
		 
		//    异步调用服务
		Future<String> future = myHystrixCommand.queue();
		//    阻塞的方法，直到拿到结果才返回
		String astring = future.get();
		
		return "调用 springcloud的远程服务"  + astring +"测试Hystrix服务熔断";
	}
	
	
}
