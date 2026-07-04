package com.anon.blogging.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
            configurer
            .requestMatchers("/", "/login", "/register").permitAll()
            .requestMatchers("/posts/new", "/posts/create").authenticated()
            .requestMatchers("/posts", "/posts/*").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form->
            form
            .loginPage("/login")
            .defaultSuccessUrl("/dashboard", true) //CHECK THIS LATER TOO
            .permitAll()
        )
        .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
