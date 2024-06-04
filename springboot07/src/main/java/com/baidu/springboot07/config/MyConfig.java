package com.baidu.springboot07.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("实现商城管理后台的测试")
                        .description("实现商城管理后台的测试")
                        .version("1.0")
                        .license(new License().name("我的测试")))
                .externalDocs(new ExternalDocumentation()
                        .description("底层功能测试"));

    }
}