package com.zerobase.springjpa100.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll();

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();   //동일 도메인에서 iframe접근 이가능하게 하는 옵션(h2콘솔이 시큐리티로인해 막혔는데, 이걸로 뚫림)
    }
}
