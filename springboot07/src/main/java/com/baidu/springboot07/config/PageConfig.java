package com.baidu.springboot07.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PageConfig {
    @Bean
    public MybatisPlusInterceptor getInterceptor() {
        //创建MybatisPlusInterceptor对象
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //创建PaginationInnerInterceptor对象
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        //把innerInterceptor添加到interceptor中
        interceptor.addInnerInterceptor(innerInterceptor);
        return interceptor;
    }
}