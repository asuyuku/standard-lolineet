/**
 * @Author zhanghongwei
 * @Date 2023 03 13 11 32
 **/
package com.lolineet.standard.auth.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") // 配置需要ADMIN角色才能访问的URL
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // 配置需要USER或ADMIN角色才能访问的URL
                .anyRequest().authenticated() // 其他所有请求需要身份验证
                .and()
                .formLogin() // 使用表单登录
                .loginPage("/login") // 指定登录页面的URL
                .permitAll() // 允许所有用户访问登录页面
                .and()
                .logout() // 配置退出登录
                .logoutUrl("/logout") // 指定退出登录的URL
                .logoutSuccessUrl("/login?logout") // 指定退出登录后要重定向的URL
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
}
