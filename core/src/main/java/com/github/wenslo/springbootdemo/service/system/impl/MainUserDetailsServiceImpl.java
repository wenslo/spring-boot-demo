package com.github.wenslo.springbootdemo.service.system.impl;

import com.github.wenslo.fluent.security.provider.MainUserDetailsService;
import com.github.wenslo.springbootdemo.reposiroty.system.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MainUserDetailsServiceImpl implements MainUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}