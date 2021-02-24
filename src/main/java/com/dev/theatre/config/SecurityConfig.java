package com.dev.theatre.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    public SecurityConfig(UserDetailsService userDetailsService,
                          PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET,"/performances/**").permitAll()
                .antMatchers(HttpMethod.GET,"/theatre-stages/**").permitAll()
                .antMatchers(HttpMethod.GET,"/performance-sessions/**").permitAll()
                .antMatchers(HttpMethod.POST,"/performances/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/performance-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/performance-sessions/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/theatre-stages/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/users/by-email/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/orders/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/orders/complete/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/shopping-carts/performance-sessions/**")
                .hasRole("USER")
                .antMatchers(HttpMethod.GET,"/shopping-carts/by-user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
