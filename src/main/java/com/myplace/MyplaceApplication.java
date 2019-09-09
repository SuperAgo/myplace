package com.myplace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = {"com.myplace.models.dao"})
@EnableCaching
public class MyplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyplaceApplication.class, args);
	}

}
