package com.suwaaaa.springcloud.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suwaaaa.springcloud.damain.User;

@RestController
public class HelloController {
	
	@GetMapping("/service/hello")
	public String helloString() {
		
		return "Hello, Spring Cloud. provider 002";
	}
	
	
	User user = new User();
	
	
	@GetMapping("/service/setUser")
	public User setUser() {
		
		user.setAddress("上海海事大学");
		user.setName("阿什利");
		user.setPhone(4456);
		
		return user;
	}
	
	
	@PostMapping("/service/postUser")
	public User getUser(@RequestParam("name")    String name,
									  @RequestParam("address") String address,
									  @RequestParam("phone")     int phone) {
		
		user.setAddress(address);
		user.setName(name);
		user.setPhone(phone);
			
		System.out.println(address+"---"+name+"---"+phone+"---"+"provider 002" +"---"+ new Date()+"---"+"/service/postUser");
		
		return user;
	}
	
	@PutMapping("/service/putUser")
	public User putUser(@RequestParam("name")    String name,
									  @RequestParam("address") String address,
									  @RequestParam("phone")     int phone) {
		
		user.setAddress(address);
		user.setName(name);
		user.setPhone(phone);
			
		System.out.println(address+"---"+name+"---"+phone+"---"+"provider 002" +"---"+ new Date()+"---"+"/service/putUser");
		
		return user;
	}
	
	@DeleteMapping("/service/deleteUser")
	public User deleteUser(@RequestParam("name")    String name,
									  @RequestParam("address") String address,
									  @RequestParam("phone")     int phone) {
		
		user.setAddress(address);
		user.setName(name);
		user.setPhone(phone);
		
		System.out.println(address+"---"+name+"---"+phone+"---"+"provider 002" +"---"+ new Date()+"---"+"/service/deleteUser");
		
		
		return user;
	}
	
}
