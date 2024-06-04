package com.baidu.springboot07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.baidu.springboot07.filter")
public class SpringBoot07Application8008 {
    public static void main(String[] args) {
        SpringApplication.run((SpringBoot07Application8008.class));
    }
}
