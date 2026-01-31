package com.medical.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients
@ComponentScan(basePackages = "com.medical.admin")
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
		 //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	       // System.out.println(encoder.encode("admin123"));
		System.out.print("Application Running ...");
}
}