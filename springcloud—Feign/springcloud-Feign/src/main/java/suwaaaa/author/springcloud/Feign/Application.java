package suwaaaa.author.springcloud.Feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients            //      开启Feign的客户端
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
