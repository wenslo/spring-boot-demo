package com.github.wenslo.springbootdemo;

import com.google.common.eventbus.EventBus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-07 20:32
 * @description
 */
@Configuration
public class CommonConfig {


    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
