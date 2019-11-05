package com.github.wenslo.springbootdemo.config;

import com.github.wenslo.fluent.security.filter.CustomAuthenticationFilter;
import com.github.wenslo.fluent.security.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
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
                .antMatchers("/login**").permitAll()
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
                .and()
                .sessionManagement()
                .maximumSessions(3)
                .sessionRegistry(sessionRegistry())

        ;
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
        super.configure(http);
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

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionAuthenticationStrategy mainSessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean

    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> servletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }
}
