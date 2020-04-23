package com.github.wenslo.springbootdemo.security;

import com.github.wenslo.fluent.security.provider.MainAuthenticationProvider;
import com.github.wenslo.fluent.security.token.CustomAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author wenhailin
 * @create 23/4/2020-15:29
 */
@Component
public class AuthenticationProvider extends MainAuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public Authentication getAuthentication(String username, String password, UserDetails userDetails) {
        CustomAuthenticationToken token = new CustomAuthenticationToken(username, password, userDetails.getAuthorities());
        token.setDetails(userDetails);
        return token;
    }
}
