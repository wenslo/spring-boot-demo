package com.github.wenslo.springbootdemo.config;

import com.github.wenslo.springbootdemo.security.filter.CustomAuthenticationFilter;
import com.github.wenslo.springbootdemo.security.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年11月24日 下午4:24
 * @description 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MainAuthenticationProvider mainAuthenticationProvider;
    @Autowired
    private MainAuthenticationEntryPoint mainAuthenticationEntryPoint;
    @Autowired
    private MainAuthenticationSuccessHandler mainAuthenticationSuccessHandler;
    @Autowired
    private MainAuthenticationFailureHandler mainAuthenticationFailureHandler;
    @Autowired
    private MainLogoutSuccessHandler mainLogoutSuccessHandler;
    @Autowired
    private MainAccessDeniedHandler mainAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/login**", "/user/export").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login_page")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessHandler(mainLogoutSuccessHandler)
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and()
                .csrf()
                .disable()
                .cors()
                .and()
                .authenticationProvider(mainAuthenticationProvider)
                .exceptionHandling()
                .authenticationEntryPoint(mainAuthenticationEntryPoint)
                .accessDeniedHandler(mainAccessDeniedHandler)

        ;
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(mainAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(mainAuthenticationFailureHandler);
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}
