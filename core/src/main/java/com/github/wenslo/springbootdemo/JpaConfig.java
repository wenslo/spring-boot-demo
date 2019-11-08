package com.github.wenslo.springbootdemo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月06日 上午10:54
 * @description
 */
@EntityScan({"com.github.wenslo.springbootdemo.model", "com.github.wenslo.fluent.model"})
@Configuration
@EnableAutoConfiguration
@EnableJpaAuditing
@EnableAsync
@EnableJpaRepositories(basePackages = {"com.github.wenslo.springbootdemo.reposiroty", "com.github.wenslo.fluent.data.repository"})
@ComponentScan(value = {"com.github.wenslo.springbootdemo", "com.github.wenslo.fluent"})
@Import({CommonConfig.class})
public class JpaConfig {

}