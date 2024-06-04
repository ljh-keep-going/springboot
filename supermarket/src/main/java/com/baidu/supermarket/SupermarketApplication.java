package com.baidu.supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.baidu.supermarket.filter")
public class SupermarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(SupermarketApplication.class, args);
	}

}
