package com.suwaaaa.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;

/**
 * Class Name: BeanConfig.java Description:
 * 
 * @bean相当于
 * 
 *          <bean id="restTemplate" class="RestTemplate"/>
 * 
 * 
 * @author suwaaaa DateTime 2021年1月27日 上午2:16:14
 * @email 1264584869@qq.com
 * @version 1.0
 */



@Configuration // 等价于一个spring applicationContext.xml 的配置文件
public class BeanConfig {

	@LoadBalanced // 加入ribbon的支持，用于实现负载均衡。
					// 在调用时，可以改为使用服务名称来访问
	@Bean
	public RestTemplate restTemplate() {

		return new RestTemplate();
	}
	
	
	
	/**
	  *  myRule
	  *  Description:
	  *                                            默认为轮询策略
	  *  切换其他的均衡规则
	  *  
	  *  @author  suwaaaa  DateTime 2021年1月28日 下午8:48:56
	  *  @email  1264584869@qq.com 
	  *  @version 1.0
	  */
	@Bean
	public IRule myRule() {
		// return new RoundRobinRule();
		//return new RandomRule();
		return new RetryRule();
	}

}
