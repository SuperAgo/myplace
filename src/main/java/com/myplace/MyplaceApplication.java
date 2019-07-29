package com.myplace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.myplace.models.dao"})
public class MyplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyplaceApplication.class, args);
	}

}
