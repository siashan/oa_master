package com.hanlin.oa.common.support.security;

import com.hanlin.oa.common.support.security.handler.PcAccessDeniedHandler;
import com.hanlin.oa.common.support.security.handler.PcAuthenticationFailureHandler;
import com.hanlin.oa.common.support.security.handler.PcAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/15 11:15
 * @Modified By:
 */
@Configuration
@EnableWebSecurity
@Order(-1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private PcAuthenticationSuccessHandler pcAuthenticationSuccessHandler;
    @Autowired
    private PcAuthenticationFailureHandler pcAuthenticationFailureHandler;
    @Autowired
    private PcAccessDeniedHandler pcAccessDeniedHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService())
                .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().disable();
        http
                // 任何尚未匹配的URL只需要验证用户即可访问
                .authorizeRequests()
                .antMatchers("/login", "dologin")
                .permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(pcAuthenticationSuccessHandler)
                .failureHandler(pcAuthenticationFailureHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(pcAccessDeniedHandler)
                // TODO 要研究下这里为什么要这么设置，POST请求才不会报403
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        ;
    }


    /**
     * 设置用户密码的加密方式
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    /**
     * 自定义UserDetailsService，授权
     *
     * @return
     */
    @Bean(name = "userDetailsService")
    public UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    /**
     * AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
