package com.github.wenslo.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * spring boot starter
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@EnableWebSecurity
@Import({CommonConfig.class, JpaConfig.class})
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
