package com.qhw.config;

import com.qhw.service.auth.AuthUserDetailsServiceImpl;
import com.qhw.util.MD5Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by asus on 2020/4/25  20:12
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        auth.userDetailsService(new AuthUserDetailsServiceImpl()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Utils.code((String)charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String encodedPassword) {
                // charSequence用户输入的
                return encodedPassword.equals(MD5Utils.code((String)charSequence));
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()   //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")           // 设置登录页面
//                .loginProcessingUrl("/user/login")  // 自定义的登录接口
                .and()
                .authorizeRequests()                // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login","/login.html").permitAll()     // 设置所有人都可以访问登录页面
//                .antMatchers("/api/**").permitAll()
                .regexMatchers("/[\\S]*\\.[\\d\\w]{1,8}").permitAll()  // 释放静态资源
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable();          // 关闭csrf防护22

        http.headers().frameOptions().sameOrigin();

    }
}