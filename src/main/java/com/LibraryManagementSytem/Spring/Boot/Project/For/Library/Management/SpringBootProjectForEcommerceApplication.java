package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootProjectForEcommerceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootProjectForEcommerceApplication.class, args);
	}

}
