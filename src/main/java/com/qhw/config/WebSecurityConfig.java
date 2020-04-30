package com.qhw.config;

import com.qhw.config.security.MyAuthenticationFailHandler;
import com.qhw.config.security.MyAuthenticationSuccessHandler;
import com.qhw.service.UserService;
import com.qhw.service.auth.AuthUserDetailsServiceImpl;
import com.qhw.service.impl.UserServiceImpl;
import com.qhw.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

/**
 * Created by asus on 2020/4/25  20:12
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailHandler failHandler;

    @Bean
    UserDetailsService UserDetailsService(){
        return new AuthUserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        auth.userDetailsService(UserDetailsService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Utils.code((String)charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String encodedPassword) {
                // charSequence用户输入的
                return encodedPassword.equals(MD5Utils.code((String) charSequence));
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()   //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")           // 设置登录页面
//                .loginProcessingUrl("/user/login")  // 自定义的登录接口
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .authorizeRequests()                // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login", "/login.html").permitAll()     // 设置所有人都可以访问登录页面
//                .antMatchers("/","index.html").permitAll()
                .antMatchers("/api/**").permitAll()
                .regexMatchers("/[\\S]*\\.[\\d\\w]{1,8}").permitAll()  // 释放静态资源
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable();          // 关闭csrf防护22

        http.headers().frameOptions().disable();
//        http.headers().frameOptions().sameOrigin();

    }

    //允许多请求地址多加斜杠  比如 /msg/list   //msg/list
    @Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall();
    }
}