package suwaaaa.author.springcloud_hystrix_dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard		//		开启服务熔断的仪表盘功能							http://localhost:8000/hystrix
public class SpringcloudHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudHystrixDashboardApplication.class, args);
	}

}
