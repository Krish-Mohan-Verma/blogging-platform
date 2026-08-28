package com.anon.blogging.Security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class UserSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        // CSRF disabled for simplicity since this is a learning project using a separate
        // React frontend with session auth. For production, re-enable CSRF with a token flow.
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(configurer ->
            configurer
            .requestMatchers("/api/register", "/login", "/logout").permitAll()
            .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/posts", "/api/posts/*").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form ->
            form
            .loginProcessingUrl("/login")
            .successHandler((request, response, authentication) -> {
                response.setStatus(HttpStatus.OK.value());
            })
            .failureHandler((request, response, exception) -> {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            })
            .permitAll()
        )
        .logout(logout ->
            logout
            .logoutSuccessHandler((request, response, authentication) -> {
                response.setStatus(HttpStatus.OK.value());
            })
            .permitAll()
        );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Vite's default dev server port
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
