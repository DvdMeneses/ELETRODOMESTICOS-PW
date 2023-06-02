package com.eletros.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/cadastrarPage").hasRole("ADMIN");
                    auth.requestMatchers("/editarPage/**").hasRole("ADMIN");
                    auth.requestMatchers("/adminPage").hasRole("ADMIN");
                    auth.requestMatchers("/verCarrinhoPage").hasRole("USER");
                    auth.anyRequest().permitAll();
                })
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .build();
    }

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

   /* @Bean
    public InMemoryUserDetailsManager userDetailsService() {
     InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();




        UserDetails user1 = User.withUsername("user1")
            .password("{noop}password1")
            .roles("USER")
            .build();
    UserDetails user2 = User.withUsername("user2")
            .password("{noop}password2")
            .roles("USER")
            .build();
    UserDetails admin = User.withUsername("admin")
            .password("{noop}password")
            .roles("ADMIN", "USER")
            .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }
*/
}